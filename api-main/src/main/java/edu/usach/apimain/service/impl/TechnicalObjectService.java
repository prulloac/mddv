package edu.usach.apimain.service.impl;

import edu.usach.apicommons.service.AbstractService;
import edu.usach.apimain.dao.TechnicalObjectDAO;
import edu.usach.apimain.model.TechnicalObject;
import edu.usach.apimain.service.ITechnicalObjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TechnicalObjectService extends AbstractService<TechnicalObject> implements ITechnicalObjectService {

	@Autowired
	TechnicalObjectDAO dao;

	@Override
	protected JpaRepository<TechnicalObject, Long> getDao() {
		return dao;
	}
}
