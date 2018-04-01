package edu.usach.apimain.dao;

import edu.usach.apimain.model.AbstractMetadataObject;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface AbstractMetadataObjectDAO extends BaseAbstractMetadataObjectDAO<AbstractMetadataObject> {
}
