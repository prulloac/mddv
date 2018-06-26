package edu.usach.apimain.service.impl;

import edu.usach.apicommons.service.EntityService;
import edu.usach.apicommons.util.TechnicalTypes;
import edu.usach.apimain.dao.TechnicalObjectDAO;
import edu.usach.apimain.model.TechnicalObject;
import edu.usach.apimain.service.ITechnicalObjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Transactional
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
        if (repositoryObject.getRepository().getType().equals(TechnicalTypes.RDBMS.getTranslation())) {
            object.put("tables", repositoryObject.getChildrenObjects().stream().filter(x -> x.getType().equals(TechnicalTypes.TABLE.getTranslation())).map(x -> {
                Map<String, Object> table = new HashMap<>();
                table.put("key", x.getName());
                table.put("columns", x.getChildrenObjects().stream().filter(y -> y.getType().equals(TechnicalTypes.COLUMN.getTranslation())).map(y -> {
                    Map<String, Object> column = new HashMap<>();
                    column.put("name", y.getName());
                    return column;
                }));
                return table;
            }).collect(Collectors.toList()));
            List<Map<String, Object>> relations = new ArrayList<>();
            repositoryObject.getChildrenObjects().stream().filter(x -> x.getType().equals(TechnicalTypes.TABLE.getTranslation())).forEach(x -> {
                x.getChildrenObjects().stream().filter(y -> y.getType().equals(TechnicalTypes.RELATION_MANY_ONE.getTranslation())).forEach(z -> {
                    Map<String, Object> relation = new HashMap<>();
                    relation.put("from", z.getDescription().split(":")[0]);
                    relation.put("to", z.getDescription().split("-\\>")[1].split(":")[0]);
                    relations.add(relation);
                });
            });
            object.put("relations", relations);
            return object;
        }
        if (repositoryObject.getRepository().getType().equals(TechnicalTypes.DOCUMENT_DB.getTranslation())) {
            object.put("collections", repositoryObject.getChildrenObjects().stream().map(x -> {
                Map<String, Object> collection = new HashMap<>();
                collection.put("key", x.getName());
                return collection;
            }).collect(Collectors.toList()));
            return object;
        }
        return null;
    }
}
