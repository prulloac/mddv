package edu.usach.apicommons.controller;

import edu.usach.apicommons.errorhandling.ApiException;
import edu.usach.apicommons.model.IEntity;
import org.springframework.http.ResponseEntity;

public interface IEntityController<T extends IEntity> {

	ResponseEntity<Object> getById(Long id, String filterString) throws ApiException;

	ResponseEntity<Object> getAll(Integer page, Integer size, String showAll) throws ApiException;

	ResponseEntity<Object> create(T entity) throws ApiException;

	ResponseEntity<Object> update(T entity) throws ApiException;

	ResponseEntity<Object> delete(T entity, Long id) throws ApiException;

}
