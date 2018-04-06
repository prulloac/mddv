package edu.usach.apicommons.service;

import edu.usach.apicommons.errorhandling.ApiException;
import edu.usach.apicommons.model.IEntity;
import org.json.simple.JSONObject;
import org.springframework.data.domain.Page;
import java.util.List;

public interface IService<T extends IEntity> {

	T findOne(final long id) throws ApiException;

	JSONObject findAndFilter(final long id, final String filterString) throws ApiException;

	List<T> findAll() throws ApiException;

	Page<T> findPaginated(int page, int size) throws ApiException;

	T create(final T entity) throws ApiException;

	T update(final T entity) throws ApiException;

	void delete(final T entity) throws ApiException;

	void deleteById(final long id) throws ApiException;

}
