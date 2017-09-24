package edu.usach.mddv.repository;

import edu.usach.mddv.model.TechnicalObject;

import javax.transaction.Transactional;

@Transactional
public interface TechnicalObjectRepository extends MetadataObjectRepository<TechnicalObject,Long>{

}
