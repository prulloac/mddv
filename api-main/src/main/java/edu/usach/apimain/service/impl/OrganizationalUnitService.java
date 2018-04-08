package edu.usach.apimain.service.impl;

import edu.usach.apicommons.service.EntityService;
import edu.usach.apimain.dao.OrganizationalUnitDAO;
import edu.usach.apimain.model.OrganizationalUnit;
import edu.usach.apimain.service.IOrganizationalUnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class OrganizationalUnitService extends EntityService<OrganizationalUnit> implements IOrganizationalUnitService {

	@Autowired
	private OrganizationalUnitDAO dao;

	@Override
	protected JpaRepository<OrganizationalUnit, Long> getDao() {
		return dao;
	}
}
