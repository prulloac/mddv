package edu.usach.apimain.service;

import edu.usach.apicommons.service.IEntityService;
import edu.usach.apimain.model.Repository;
import org.json.simple.JSONArray;

import java.util.List;

public interface IRepositoryService extends IEntityService<Repository>{
	Object extractFromRepository(long id, String token);

	List<Repository> extractables();

	List extractableTypes();

	JSONArray getConnectionParams(Long id, String token);
}
