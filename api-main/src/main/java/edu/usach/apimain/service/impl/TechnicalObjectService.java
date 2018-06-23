package edu.usach.apimain.service.impl;

import edu.usach.apicommons.service.EntityService;
import edu.usach.apimain.dao.TechnicalObjectDAO;
import edu.usach.apimain.model.TechnicalObject;
import edu.usach.apimain.service.ITechnicalObjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class TechnicalObjectService extends EntityService<TechnicalObject> implements ITechnicalObjectService {

	@Autowired
	private TechnicalObjectDAO dao;
	@Autowired
	private RepositoryService repositoryService;

	@Override
	protected JpaRepository<TechnicalObject, Long> getDao() {
		return dao;
	}

	@Override
	public List<TechnicalObject> getRepositories(String token) {
		return dao
				.findRepositories()
				.stream()
				.filter(x -> repositoryService.testConnection(x.getRepository().getId(), token))
				.collect(Collectors.toList());
	}

	@Override
	public Object getChildrenObjects(Long id, String token) {
		return dao.findChildrenObjects(id);
	}
}
