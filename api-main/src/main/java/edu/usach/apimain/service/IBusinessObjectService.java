package edu.usach.apimain.service;

import edu.usach.apicommons.service.IEntityService;
import edu.usach.apimain.model.BusinessObject;

import java.util.List;
import java.util.Map;

public interface IBusinessObjectService extends IEntityService<BusinessObject> {
    Object updateRelations(Long id, List<Map<String, Object>> relatedObjects);
}
