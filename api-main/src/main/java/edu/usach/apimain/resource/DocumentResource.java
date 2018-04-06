package edu.usach.apimain.resource;

import edu.usach.apicommons.resource.AbstractResource;
import edu.usach.apicommons.service.IService;
import edu.usach.apimain.model.Document;
import edu.usach.apimain.service.IDocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@CrossOrigin(maxAge = 7200)
@RestController
@RequestMapping("/documents")
public class DocumentResource extends AbstractResource<Document> {

	@Autowired
	IDocumentService service;

	@Override
	protected IService getService() {
		return service;
	}
}
