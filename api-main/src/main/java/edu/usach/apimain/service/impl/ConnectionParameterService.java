package edu.usach.apimain.service.impl;

import edu.usach.apicommons.service.EntityService;
import edu.usach.apimain.dao.ConnectionParamDAO;
import edu.usach.apimain.model.ConnectionParameter;
import edu.usach.apimain.service.IConnectionParameterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ConnectionParameterService extends EntityService<ConnectionParameter> implements IConnectionParameterService {

	@Autowired
	private ConnectionParamDAO dao;
	@Override
	protected JpaRepository<ConnectionParameter, Long> getDao() {
		return dao;
	}
}
