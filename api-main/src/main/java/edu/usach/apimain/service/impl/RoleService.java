package edu.usach.apimain.service.impl;

import edu.usach.apicommons.service.EntityService;
import edu.usach.apimain.dao.RoleDAO;
import edu.usach.apimain.model.Role;
import edu.usach.apimain.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class RoleService extends EntityService<Role> implements IRoleService {

	@Autowired
	private RoleDAO dao;

	@Override
	protected JpaRepository<Role, Long> getDao() {
		return dao;
	}
}
