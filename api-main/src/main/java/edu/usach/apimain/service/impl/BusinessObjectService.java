package edu.usach.apimain.service.impl;

import edu.usach.apicommons.service.EntityService;
import edu.usach.apimain.dao.BusinessObjectDAO;
import edu.usach.apimain.model.BusinessObject;
import edu.usach.apimain.service.IBusinessObjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class BusinessObjectService extends EntityService<BusinessObject> implements IBusinessObjectService {

	@Autowired
	private BusinessObjectDAO dao;

	@Override
	protected JpaRepository<BusinessObject, Long> getDao() {
		return dao;
	}
}
