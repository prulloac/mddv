package edu.usach.apimain.service.impl;

import edu.usach.apicommons.annotations.ServiceOfEntity;
import edu.usach.apicommons.service.AbstractService;
import edu.usach.apimain.dao.BusinessObjectDAO;
import edu.usach.apimain.model.BusinessObject;
import edu.usach.apimain.service.IBusinessObjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@ServiceOfEntity("BusinessObject")
public class BusinessObjectService extends AbstractService<BusinessObject> implements IBusinessObjectService {

	@Autowired
	BusinessObjectDAO dao;

	@Override
	protected PagingAndSortingRepository<BusinessObject, Long> getDao() {
		return dao;
	}
}
