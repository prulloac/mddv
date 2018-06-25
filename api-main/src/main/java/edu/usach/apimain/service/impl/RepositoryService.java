package edu.usach.apimain.service.impl;

import edu.usach.apicommons.errorhandling.ApiException;
import edu.usach.apicommons.errorhandling.ErrorCode;
import edu.usach.apicommons.service.EntityService;
import edu.usach.apicommons.util.TechnicalTypes;
import edu.usach.apimain.dao.ConnectionParamDAO;
import edu.usach.apimain.dao.ExtractorDAO;
import edu.usach.apimain.dao.RepositoryDAO;
import edu.usach.apimain.dao.TechnicalObjectDAO;
import edu.usach.apimain.dto.RepositoryDTO;
import edu.usach.apimain.model.ConnectionParameter;
import edu.usach.apimain.model.Extractor;
import edu.usach.apimain.model.Repository;
import edu.usach.apimain.model.TechnicalObject;
import edu.usach.apimain.service.IRepositoryService;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;

@Service
@Transactional
@Slf4j
public class RepositoryService extends EntityService<Repository> implements IRepositoryService {
    @Autowired
    private RepositoryDAO dao;
    @Autowired
    private ExtractorDAO extractorDAO;
    @Autowired
    private ExtractorService extractorService;
    @Autowired
    private ConnectionParamDAO connectionParamDAO;
    @Autowired
    private TechnicalObjectDAO technicalObjectDAO;

    @Override
    protected JpaRepository<Repository, Long> getDao() {
        return dao;
    }

    @Override
    public Object extractFromRepository(long id, String token) throws ApiException {
        Repository repository = dao.findById(id).get();
        String engine = repository.getEngine();
        String version = repository.getVersion();
        JSONObject connectionParams = new JSONObject();
        repository.getConnectionParameters().forEach(x -> connectionParams.put(x.getName(), x.getValue()));
        try {
            TechnicalObject parentObject = technicalObjectDAO.findRepositories().stream().filter(x -> x.getRepository().equals(repository)).findFirst().get();
            Map<String, Object> data = (Map<String, Object>) extractorService.callExtractor(engine, version, connectionParams, token).get("data");
            technicalObjectDAO.findByRepository(repository).stream().filter(x -> x.getParentObject() != null).forEach(technicalObjectDAO::delete);
            if (repository.getType().equals(TechnicalTypes.RDBMS.getTranslation())) {
                ((List<Map<String, Object>>) data.get("tables")).forEach(x -> {
                    TechnicalObject table = new TechnicalObject();
                    table.setRepository(repository);
                    table.setName(x.get("tableName").toString());
                    table.setParentObject(parentObject);
                    table.setType(TechnicalTypes.TABLE.getTranslation());
                    table.setVersion("1.0");
                    technicalObjectDAO.saveAndFlush(table);
                    ((List<Map<String, Object>>) x.get("columns")).forEach(y -> {
                        TechnicalObject column = new TechnicalObject();
                        column.setRepository(repository);
                        column.setParentObject(table);
                        column.setName(y.get("columnName").toString());
                        column.setType(TechnicalTypes.COLUMN.getTranslation());
                        column.setVersion("1.0");
                        column.setDescription("column type: " + y.get("columnType") + "\nnullable: " + y.get("nullable") + "\nautoIncrement: " + y.get("autoIncrement"));
                        technicalObjectDAO.saveAndFlush(column);
                    });
                });
                ((List<Map<String, Object>>) data.get("relations")).forEach(x -> {
                    TechnicalObject relation = new TechnicalObject();
                    relation.setName(x.get("sourceColumn") + " -> " + x.get("destinyColumn"));
                    relation.setType(TechnicalTypes.RELATION_MANY_ONE.getTranslation());
                    relation.setVersion("1.0");
                    relation.setRepository(repository);
                    relation.setParentObject(technicalObjectDAO.findByNameAndRepository(x.get("sourceTable"), repository));
                    technicalObjectDAO.saveAndFlush(relation);
                });
            }
            if (repository.getType().equals(TechnicalTypes.DOCUMENT_DB.getTranslation())) {
                ((List<Map<String, Object>>) data.get("collections")).forEach(x -> {
                    TechnicalObject collection = new TechnicalObject();
                    collection.setRepository(repository);
                    collection.setName(x.get("collectionName").toString());
                    collection.setParentObject(parentObject);
                    collection.setType(TechnicalTypes.COLLECTION.getTranslation());
                    collection.setVersion("1.0");
                    technicalObjectDAO.saveAndFlush(collection);
                });
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return false;
        }
        return true;
    }

    @Override
    public List<Repository> extractables() {
        List<Repository> repositories = dao.findAll();
        List<Extractor> extractors = extractorDAO.findAll();
        repositories.
                removeIf(x -> extractors.stream().
                        noneMatch(y -> (y.getSupportedEngine().equalsIgnoreCase(x.getEngine()) && y.getSupportedVersions().contains(x.getVersion()))));
        return repositories;
    }

    @Override
    public Object extractableTypes() {
        List<Map<String, Object>> extractors = new ArrayList<>();
        Map<String, List<Extractor>> groupedByEngine = extractorDAO.findAll().stream().collect(groupingBy(Extractor::getSupportedEngine));
        groupedByEngine.forEach((k, v) -> {
            Map<String, Object> engine = new HashMap<>();
            engine.put("engine", k);
            Set<String> versions = new HashSet<>();
            v.forEach(x -> Collections.addAll(versions, x.getSupportedVersions().split(",")));
            engine.put("versions", versions);
            extractors.add(engine);
        });
        return extractors;
    }

    @Override
    public JSONArray getConnectionParams(Long id, String token) throws ApiException {
        Repository repository = dao.findById(id).orElse(null);
        if (null == repository) throw new ApiException(ErrorCode.OBJECT_NOT_FOUND, "Repository");
        return extractorService.getExtractorParams(repository.getEngine(), repository.getVersion(), token);
    }

    @Override
    public Object putConnectionParams(Long id, Map<String, Object> params) {
        Repository repository = dao.findById(id).orElse(null);
        if (null == repository) throw new ApiException(ErrorCode.OBJECT_NOT_FOUND, "Repository");
        List<ConnectionParameter> connectionParameters = new ArrayList<>();
        repository.getConnectionParameters().forEach(x -> connectionParamDAO.delete(x));
        params.keySet().forEach(x -> {
            ConnectionParameter parameter = new ConnectionParameter();
            parameter.setName(x);
            parameter.setValue(params.get(x).toString());
            parameter.setRepository(repository);
            connectionParamDAO.saveAndFlush(parameter);
            connectionParameters.add(parameter);
        });
        repository.setConnectionParameters(connectionParameters);
        dao.saveAndFlush(repository);
        return true;
    }

    public Repository create(Repository entity, String token) {
        JSONObject extractorInfo = extractorService.getExtractorInfo(entity.getEngine(), entity.getVersion(), token);
        entity.setType((String) extractorInfo.get("type"));
        dao.saveAndFlush(entity);
        TechnicalObject repositoryObject = new TechnicalObject();
        repositoryObject.setRepository(entity);
        repositoryObject.setType(TechnicalTypes.REPOSITORY.getTranslation() + " " + entity.getType());
        repositoryObject.setVersion("1.0");
        repositoryObject.setName(entity.getName());
        repositoryObject.setDescription("Motor: " + entity.getEngine() + "(" + entity.getVersion() + ")");
        technicalObjectDAO.saveAndFlush(repositoryObject);
        return entity;
    }

    public List<RepositoryDTO> findAllDTO() throws ApiException {
        return this.findAll().stream().map(RepositoryDTO::new).collect(Collectors.toList());
    }

    public Page<RepositoryDTO> findPaginatedDTO(Integer page, Integer size) throws ApiException {
        return this.findPaginated(page, size).map(RepositoryDTO::new);
    }

    @Override
    public Boolean testConnection(Long id, String token) {
        Repository repository = dao.findById(id).get();
        Map<String, Object> connectionParams = new HashMap<>();
        connectionParamDAO.findByRepository(repository).forEach(x -> {
            connectionParams.put(x.getName(), x.getValue());
        });
        return extractorService.testConnection(repository.getEngine(), repository.getVersion(), token, connectionParams);
    }

    @Override
    public RepositoryDTO findOneDTO(Long id) {
        return new RepositoryDTO(dao.findById(id).orElse(new Repository()));
    }
}
