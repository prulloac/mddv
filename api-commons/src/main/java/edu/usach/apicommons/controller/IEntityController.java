package edu.usach.apicommons.controller;

import edu.usach.apicommons.model.IEntity;
import org.springframework.http.ResponseEntity;

public interface IEntityController<T extends IEntity> {

	ResponseEntity getById(Long id);

	ResponseEntity getByIdAndFilterOutput(Long id, String filterString);

	ResponseEntity getAll();

	ResponseEntity getAllPaginated(int page, int size);

	ResponseEntity getAllPaginated(int size);

	ResponseEntity create(T entity);

	ResponseEntity update(T entity);

	ResponseEntity delete(T entity);

	ResponseEntity deleteById(long id);

}
