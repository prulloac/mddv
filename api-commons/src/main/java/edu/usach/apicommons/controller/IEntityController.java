package edu.usach.apicommons.controller;

import edu.usach.apicommons.model.IEntity;
import org.springframework.http.ResponseEntity;

public interface IEntityController<T extends IEntity> {

	ResponseEntity<Object> getById(Long id);

	ResponseEntity<Object> getByIdAndFilterOutput(Long id, String filterString);

	ResponseEntity<Object> getAll();

	ResponseEntity<Object> getAllPaginated(int page, int size);

	ResponseEntity<Object> getAllPaginated(int size);

	ResponseEntity<Object> create(T entity);

	ResponseEntity<Object> update(T entity);

	ResponseEntity<Object> delete(T entity);

	ResponseEntity<Object> deleteById(long id);

}
