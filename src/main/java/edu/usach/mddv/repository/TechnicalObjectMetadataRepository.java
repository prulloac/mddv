package edu.usach.mddv.repository;

import edu.usach.mddv.model.TechnicalObjectMetadata;

import javax.transaction.Transactional;

@Transactional
public interface TechnicalObjectMetadataRepository extends AbstractObjectMetadataRepository<TechnicalObjectMetadata, Long>{
}
