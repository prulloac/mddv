package edu.usach.apimain.service.impl;

import edu.usach.apicommons.annotations.ServiceOfEntity;
import edu.usach.apicommons.service.AbstractService;
import edu.usach.apimain.dao.DocumentDAO;
import edu.usach.apimain.model.Document;
import edu.usach.apimain.service.IDocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@ServiceOfEntity("Document")
public class DocumentService extends AbstractService<Document> implements IDocumentService {

	@Autowired
	DocumentDAO dao;

	@Override
	protected PagingAndSortingRepository<Document, Long> getDao() {
		return dao;
	}
}
