package edu.usach.apimain.service.impl;

import edu.usach.apicommons.annotations.ServiceOfEntity;
import edu.usach.apicommons.service.AbstractService;
import edu.usach.apimain.dao.RepositoryDAO;
import edu.usach.apimain.model.Repository;
import edu.usach.apimain.service.IRepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@ServiceOfEntity("Repository")
public class RepositoryService extends AbstractService<Repository> implements IRepositoryService {
	@Autowired
	RepositoryDAO dao;

	@Override
	protected PagingAndSortingRepository<Repository, Long> getDao() {
		return dao;
	}
}
