package edu.usach.apimain.service.impl;

import edu.usach.apicommons.service.AbstractService;
import edu.usach.apimain.dao.RoleDAO;
import edu.usach.apimain.model.Role;
import edu.usach.apimain.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class RoleService extends AbstractService<Role> implements IRoleService {

	@Autowired
	RoleDAO dao;

	@Override
	protected PagingAndSortingRepository<Role, Long> getDao() {
		return dao;
	}
}
