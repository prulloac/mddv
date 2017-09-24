package edu.usach.mddv.repository;

import edu.usach.mddv.model.BusinessObject;

import javax.transaction.Transactional;

@Transactional
public interface BusinessObjectRepository extends MetadataObjectRepository<BusinessObject, Long>{

}
