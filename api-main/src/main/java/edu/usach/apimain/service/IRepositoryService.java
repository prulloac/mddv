package edu.usach.apimain.service;

import edu.usach.apicommons.service.IEntityService;
import edu.usach.apimain.dto.RepositoryDTO;
import edu.usach.apimain.model.Repository;
import org.json.simple.JSONArray;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;

public interface IRepositoryService extends IEntityService<Repository>{
	Object extractFromRepository(long id, String token);

	List<Repository> extractables();

	Object extractableTypes();

	JSONArray getConnectionParams(Long id, String token);

	Object putConnectionParams(Long id, Map<String, Object> params);

	List<RepositoryDTO> findAllDTO();

	Page<RepositoryDTO> findPaginatedDTO(Integer page, Integer size);

	Boolean testConnection(Long id, String token);

	RepositoryDTO findOneDTO(Long id);

	Repository create(Repository entity, String token);
}
