package edu.usach.apimain.controller;

import edu.usach.apicommons.controller.EntityController;
import edu.usach.apicommons.service.IEntityService;
import edu.usach.apimain.model.MetadataObject;
import edu.usach.apimain.service.IMetadataObjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(maxAge = 7200)
@RestController
@RequestMapping("/metadata-objects")
public class MetadataObjectController extends EntityController<MetadataObject> {

	@Autowired
	private IMetadataObjectService service;

	@Override
	protected IEntityService<MetadataObject> getService() {
		return service;
	}
}
