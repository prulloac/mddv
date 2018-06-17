package edu.usach.apimain.service.impl;

import edu.usach.apicommons.errorhandling.ApiException;
import edu.usach.apicommons.errorhandling.ErrorCode;
import edu.usach.apicommons.service.EntityService;
import edu.usach.apimain.dao.ConnectionParamDAO;
import edu.usach.apimain.dao.ExtractorDAO;
import edu.usach.apimain.dao.RepositoryDAO;
import edu.usach.apimain.dao.TechnicalObjectDAO;
import edu.usach.apimain.model.ConnectionParameter;
import edu.usach.apimain.model.Extractor;
import edu.usach.apimain.model.Repository;
import edu.usach.apimain.model.TechnicalObject;
import edu.usach.apimain.service.IRepositoryService;
import edu.usach.apimain.util.TechnicalTypes;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Transactional
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
		String engine = repository.getType();
		String version = repository.getVersion();
		JSONObject connectionParams = new JSONObject();
		repository.getConnectionParameters().forEach(x -> connectionParams.put(x.getName(), x.getValue()));
		return extractorService.callExtractor(engine, version, connectionParams, token);
	}

	@Override
	public List<Repository> extractables() {
		List<Repository> repositories = dao.findAll();
		List<Extractor> extractors = extractorDAO.findAll();
		repositories.
				removeIf(x -> extractors.stream().
						noneMatch(y -> (y.getSupportedEngine().equalsIgnoreCase(x.getType()) && y.getSupportedVersions().contains(x.getVersion()))));
		return repositories;
	}

	@Override
	public List extractableTypes() {
		List<Extractor>	extractors = extractorDAO.findAll();
		return extractors.stream().map(x -> {
			JSONObject object = new JSONObject();
			object.put("type",x.getSupportedEngine());
			object.put("versions",x.getSupportedVersions().split(","));
			return object;
		}).collect(Collectors.toList());
	}

	@Override
	public JSONArray getConnectionParams(Long id, String token) throws ApiException {
		Repository repository = dao.findById(id).orElse(null);
		if (null == repository) throw new ApiException(ErrorCode.OBJECT_NOT_FOUND, "Repository");
		return extractorService.getExtractorParams(repository.getType(), repository.getVersion(), token);
	}

	@Override
	public Object putConnectionParams(Long id, Map<String, Object> params) {
		Repository repository = dao.findById(id).orElse(null);
		if (null == repository) throw new ApiException(ErrorCode.OBJECT_NOT_FOUND, "Repository");
		params.keySet().forEach(x -> {
			ConnectionParameter parameter = new ConnectionParameter();
			parameter.setName(x);
			parameter.setValue(params.get(x).toString());
			parameter.setRepository(repository);
			connectionParamDAO.saveAndFlush(parameter);
		});
		return true;
	}

	@Override
	public Repository create(Repository entity) {
		TechnicalObject repositoryObject = new TechnicalObject();
		repositoryObject.setRepository(entity);
		repositoryObject.setType(TechnicalTypes.REPOSITORY.toString());
		repositoryObject.setVersion("1.0.0");
		repositoryObject.setName(entity.getName());
		technicalObjectDAO.saveAndFlush(repositoryObject);
		return super.create(entity);
	}
}
