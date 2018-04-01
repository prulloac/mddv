package edu.usach.apimain.dao;

import edu.usach.apimain.model.BusinessObject;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface BusinessObjectDAO extends BaseAbstractMetadataObjectDAO<BusinessObject> {
}
