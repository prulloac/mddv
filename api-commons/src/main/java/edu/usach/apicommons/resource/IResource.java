package edu.usach.apicommons.resource;

import org.springframework.http.ResponseEntity;

import java.io.Serializable;

public interface IResource<T extends Serializable> {

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
