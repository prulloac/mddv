package edu.usach.mddv.repository;

import edu.usach.mddv.model.BusinessObjectMetadata;

import javax.transaction.Transactional;

@Transactional
public interface BusinessObjectMetadataRepository extends AbstractObjectMetadataRepository<BusinessObjectMetadata,Long>{
}
