package edu.usach.apimain.service;

import edu.usach.apicommons.service.IEntityService;
import edu.usach.apimain.model.TechnicalObject;

public interface ITechnicalObjectService extends IEntityService<TechnicalObject> {
	Object getRepositories(String token);

	Object getChildrenObjects(Long id, String token);
}
