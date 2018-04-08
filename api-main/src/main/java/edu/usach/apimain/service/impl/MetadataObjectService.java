package edu.usach.apimain.service.impl;

import edu.usach.apicommons.service.EntityService;
import edu.usach.apimain.dao.MetadataObjectDAO;
import edu.usach.apimain.model.MetadataObject;
import edu.usach.apimain.service.IMetadataObjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class MetadataObjectService extends EntityService<MetadataObject> implements IMetadataObjectService {

	@Autowired
	private MetadataObjectDAO dao;

	@Override
	protected JpaRepository<MetadataObject, Long> getDao() {
		return dao;
	}
}
