package edu.usach.apimain.service;

import edu.usach.apicommons.errorhandling.ApiException;
import edu.usach.apicommons.service.IEntityService;
import edu.usach.apimain.model.Repository;

import java.util.List;

public interface IRepositoryService extends IEntityService<Repository>{
	Object extractFromRepository(long id) throws ApiException;

	List<Repository> extractables();

}
