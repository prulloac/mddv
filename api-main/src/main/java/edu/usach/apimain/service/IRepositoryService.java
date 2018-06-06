package edu.usach.apimain.service;

import edu.usach.apicommons.errorhandling.ApiException;
import edu.usach.apicommons.service.IEntityService;
import edu.usach.apimain.model.Repository;
import org.json.simple.JSONObject;

import java.util.List;
import java.util.Map;

public interface IRepositoryService extends IEntityService<Repository>{
	Object extractFromRepository(long id) throws ApiException;

	List<Repository> extractables();

	List extractableTypes();

	JSONObject getConnectionParams(Long id) throws ApiException;
}
