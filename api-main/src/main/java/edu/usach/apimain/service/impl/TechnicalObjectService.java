package edu.usach.apimain.service.impl;

import edu.usach.apicommons.errorhandling.ApiException;
import edu.usach.apicommons.errorhandling.ErrorCode;
import edu.usach.apicommons.service.EntityService;
import edu.usach.apicommons.util.TechnicalTypes;
import edu.usach.apimain.dao.TechnicalObjectDAO;
import edu.usach.apimain.model.TechnicalObject;
import edu.usach.apimain.service.ITechnicalObjectService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@Transactional
@Slf4j
public class TechnicalObjectService extends EntityService<TechnicalObject> implements ITechnicalObjectService {

    @Autowired
    private TechnicalObjectDAO dao;

    @Override
    protected JpaRepository<TechnicalObject, Long> getDao() {
        return dao;
    }

    @Override
    public List<TechnicalObject> getRepositories(String token) {
        return dao.findRepositories();
    }

    @Override
    public Object getChildrenObjects(Long id, String token) {
        return dao.findChildrenObjects(id);
    }

    @Override
    public Object getObjectsFromRepository(Long id) {
        Map<String, Object> object = new HashMap<>();
        TechnicalObject repositoryObject = dao.findByRepositoryIdAndParentObjectIsNull(id);
        List<Map<String, Object>> nodes = new ArrayList<>();
        List<Map<String, Object>> links = new ArrayList<>();
        log.info("repository: {}", repositoryObject.getName());
        if (repositoryObject.getType().equalsIgnoreCase(TechnicalTypes.RDBMS.getTranslation())) {
            nodes = repositoryObject.getChildrenObjects().stream().filter(x -> x.getType().equals(TechnicalTypes.TABLE.getTranslation())).map(x -> {
                log.info("table: {}", x.getName());
                Map<String, Object> table = new HashMap<>();
                table.put("name", x.getName());
                table.put("category", x.getType());
                table.put("items", x.getChildrenObjects().stream().filter(y -> y.getType().equals(TechnicalTypes.COLUMN.getTranslation())).map(y -> {
                    log.info("column: {}", y.getName());
                    Map<String, Object> column = new HashMap<>();
                    column.put("name", y.getName());
                    column.put("type", y.getDescription().split("\\nnullable")[0].replaceAll("column type: ", ""));
                    return column;
                }));
                return table;
            }).collect(Collectors.toList());
            repositoryObject.getChildrenObjects().stream().filter(x -> x.getType().equals(TechnicalTypes.TABLE.getTranslation())).forEach(x -> {
                x.getChildrenObjects().stream().filter(y -> y.getType().equals(TechnicalTypes.RELATION_MANY_ONE.getTranslation())).forEach(z -> {
                    log.info("relation: {}", z.getName());
                    Map<String, Object> relation = new HashMap<>();
                    relation.put("from", z.getDescription().split(":")[0]);
                    relation.put("to", z.getDescription().split("-\\>")[1].split(":")[0]);
                    relation.put("category", z.getType());
                    links.add(relation);
                });
            });
        }
        if (repositoryObject.getType().equalsIgnoreCase(TechnicalTypes.DOCUMENT_DB.getTranslation())) {
            nodes = repositoryObject.getChildrenObjects().stream().filter(x -> x.getType().equals(TechnicalTypes.COLLECTION.getTranslation())).map(x -> {
                log.info("collection: {}", x.getName());
                Map<String, Object> table = new HashMap<>();
                table.put("name", x.getName());
                table.put("category", x.getType());
                table.put("items", x.getChildrenObjects().stream().filter(y -> y.getType().equals(TechnicalTypes.COLLECTION_ATTRIBUTE.getTranslation())).map(y -> {
                    log.info("attribute: {}", y.getName());
                    Map<String, Object> column = new HashMap<>();
                    column.put("name", y.getName());
                    column.put("type", y.getDescription() != null ? y.getDescription() : "any");
                    return column;
                }));
                return table;
            }).collect(Collectors.toList());
            repositoryObject.getChildrenObjects().stream().filter(x -> x.getType().equals(TechnicalTypes.COLLECTION.getTranslation())).forEach(x -> {
                x.getChildrenObjects().stream().filter(y ->
                        y.getType().equals(TechnicalTypes.RELATION_USE.getTranslation()) ||
                        y.getType().equals(TechnicalTypes.RELATION_GENERALIZATION.getTranslation()) ||
                        y.getType().equals(TechnicalTypes.RELATION_DEPENDENCY.getTranslation())).forEach(z -> {
                    log.info("link: {}", z.getName());
                    Map<String, Object> relation = new HashMap<>();
                    relation.put("from", z.getDescription().split(":")[0]);
                    relation.put("to", z.getDescription().split("-\\>")[1].split(":")[0]);
                    relation.put("category", z.getType());
                    links.add(relation);
                });
            });
        }
        object.put("nodes", nodes);
        object.put("links", links);
        return object;
    }

    @Override
    public Object create(TechnicalObject entity, Long parent) {
        try {
            TechnicalObject parentEntity = dao.findById(parent).orElse(null);
            if (parentEntity != null) {
                entity.setParentObject(parentEntity);
                entity.setRepository(parentEntity.getRepository());
                List<TechnicalObject> siblings = parentEntity.getChildrenObjects();
                siblings.add(entity);
                parentEntity.setChildrenObjects(siblings);
                dao.saveAndFlush(parentEntity);
            }
            dao.saveAndFlush(entity);
            return entity;
        } catch (ApiException e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }

    @Override
    public Object getTechnicalObjectTypes(Long parentId) {
        Stream<TechnicalTypes> typesStream = Arrays.stream(TechnicalTypes.values());
        TechnicalObject parentObject = dao.findById(parentId).orElse(null);
        if (parentObject != null) {
            typesStream = typesStream
                    .filter(x -> x.getParent() != null)
                    .filter(x -> x.getParent().getTranslation().equalsIgnoreCase(parentObject.getType()));
        } else {
            typesStream = typesStream
                    .filter(x -> x.getParent() != null)
                    .filter(x -> x.getParent().ordinal() == 0);
        }
        return typesStream.map(x -> {
            Map<String, Object> object = new HashMap<>();
            object.put("id", x.ordinal());
            object.put("name", x);
            object.put("translation", x.getTranslation());
            object.put("type", x.getType());
            return object;
        }).collect(Collectors.toList());
    }
}
