package edu.usach.apimain.service.impl;

import edu.usach.apicommons.annotations.ServiceOfEntity;
import edu.usach.apicommons.service.AbstractService;
import edu.usach.apimain.dao.ConnectionParamDAO;
import edu.usach.apimain.model.ConnectionParameter;
import edu.usach.apimain.service.IConnectionParameterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@ServiceOfEntity("ConnectionParameter")
public class ConnectionParameterService extends AbstractService<ConnectionParameter> implements IConnectionParameterService {

	@Autowired
	ConnectionParamDAO dao;
	@Override
	protected PagingAndSortingRepository<ConnectionParameter, Long> getDao() {
		return dao;
	}
}
