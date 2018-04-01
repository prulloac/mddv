package edu.usach.apicommons.service;

import edu.usach.apicommons.errorhandling.ApiException;
import org.springframework.data.domain.Page;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public interface IService<T extends Serializable> {

	T findOne(final long id) throws ApiException;

	Map<String, Object> convertToMap(final long id, final String filterString) throws ApiException;

	List<T> findAll() throws ApiException;

	Page<T> findPaginated(int page, int size) throws ApiException;

	T create(final T entity) throws ApiException;

	T update(final T entity) throws ApiException;

	void delete(final T entity) throws ApiException;

	void deleteById(final long id) throws ApiException;

}
