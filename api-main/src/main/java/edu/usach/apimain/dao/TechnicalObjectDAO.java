package edu.usach.apimain.dao;

import edu.usach.apimain.model.TechnicalObject;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface TechnicalObjectDAO extends BaseMetadataObjectDAO<TechnicalObject> {

	@Query("select T from TechnicalObject T where T.parentObject is null")
	List<TechnicalObject> findRepositories();
}
