package edu.usach.apimain.dao;

import edu.usach.apimain.model.Repository;
import edu.usach.apimain.model.TechnicalObject;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface TechnicalObjectDAO extends BaseMetadataObjectDAO<TechnicalObject> {

    @Query("select T from TechnicalObject T where T.parentObject is null")
    List<TechnicalObject> findRepositories();

    @Query("select T from TechnicalObject T where T.parentObject.id = ?1")
    List<TechnicalObject> findChildrenObjects(Long id);

    TechnicalObject findByNameAndRepository(Object sourceTable, Repository repository);

    List<TechnicalObject> findByRepository(Repository repository);

    TechnicalObject findByRepositoryIdAndParentObjectIsNull(Long id);
}
