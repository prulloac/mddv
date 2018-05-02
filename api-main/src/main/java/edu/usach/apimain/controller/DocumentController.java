package edu.usach.apimain.controller;

import edu.usach.apicommons.controller.EntityController;
import edu.usach.apicommons.service.IEntityService;
import edu.usach.apimain.model.Document;
import edu.usach.apimain.service.IDocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(maxAge = 7200)
@RestController
@RequestMapping("/documents")
public class DocumentController extends EntityController<Document> {

	@Autowired
	private IDocumentService service;

	@Override
	protected IEntityService<Document> getService() {
		return service;
	}
}
