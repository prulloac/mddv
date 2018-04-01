package edu.usach.apimain.dao;

import edu.usach.apimain.model.TechnicalObject;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface TechnicalObjectDAO extends BaseAbstractMetadataObjectDAO<TechnicalObject> {
}
