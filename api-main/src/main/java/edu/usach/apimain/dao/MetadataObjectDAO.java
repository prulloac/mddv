package edu.usach.apimain.dao;

import edu.usach.apimain.model.MetadataObject;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface MetadataObjectDAO extends BaseMetadataObjectDAO<MetadataObject> {
}
