package edu.usach.apimain.service.impl;

import edu.usach.apicommons.service.EntityService;
import edu.usach.apimain.dao.DocumentDAO;
import edu.usach.apimain.model.Document;
import edu.usach.apimain.service.IDocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class DocumentService extends EntityService<Document> implements IDocumentService {

	@Autowired
	private DocumentDAO dao;

	@Override
	protected JpaRepository<Document, Long> getDao() {
		return dao;
	}
}
