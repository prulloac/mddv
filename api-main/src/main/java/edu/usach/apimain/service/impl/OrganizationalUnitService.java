package edu.usach.apimain.service.impl;

import edu.usach.apicommons.annotations.ServiceOfEntity;
import edu.usach.apicommons.service.AbstractService;
import edu.usach.apimain.dao.OrganizationalUnitDAO;
import edu.usach.apimain.model.OrganizationalUnit;
import edu.usach.apimain.service.IOrganizationalUnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@ServiceOfEntity("OrganizationalUnit")
public class OrganizationalUnitService extends AbstractService<OrganizationalUnit> implements IOrganizationalUnitService {

	@Autowired
	OrganizationalUnitDAO dao;

	@Override
	protected PagingAndSortingRepository<OrganizationalUnit, Long> getDao() {
		return dao;
	}
}
