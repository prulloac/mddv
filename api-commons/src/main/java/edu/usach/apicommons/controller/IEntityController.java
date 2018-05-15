package edu.usach.apicommons.controller;

import edu.usach.apicommons.model.IEntity;
import org.springframework.http.ResponseEntity;

public interface IEntityController<T extends IEntity> {

	ResponseEntity<Object> getById(Long id, String filterString);

	ResponseEntity<Object> getAll(Integer page, Integer size, String showAll);

	ResponseEntity<Object> create(T entity);

	ResponseEntity<Object> update(T entity);

	ResponseEntity<Object> delete(T entity, Long id);

}
