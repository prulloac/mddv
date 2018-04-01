package edu.usach.apimain.service.impl;

import edu.usach.apicommons.annotations.ServiceOfEntity;
import edu.usach.apicommons.service.AbstractService;
import edu.usach.apimain.dao.AbstractMetadataObjectDAO;
import edu.usach.apimain.model.AbstractMetadataObject;
import edu.usach.apimain.service.IAbstractMetadataObjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@ServiceOfEntity("AbstractMetadataObject")
public class AbstractMetadataObjectService extends AbstractService<AbstractMetadataObject> implements IAbstractMetadataObjectService {

	@Autowired
	AbstractMetadataObjectDAO dao;

	@Override
	protected PagingAndSortingRepository<AbstractMetadataObject, Long> getDao() {
		return dao;
	}
}
