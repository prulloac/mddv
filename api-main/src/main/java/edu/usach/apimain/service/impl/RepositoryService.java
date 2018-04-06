package edu.usach.apimain.service.impl;

import edu.usach.apicommons.service.AbstractService;
import edu.usach.apimain.dao.RepositoryDAO;
import edu.usach.apimain.model.Repository;
import edu.usach.apimain.service.IRepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class RepositoryService extends AbstractService<Repository> implements IRepositoryService {
	@Autowired
	RepositoryDAO dao;

	@Override
	protected JpaRepository<Repository, Long> getDao() {
		return dao;
	}
}