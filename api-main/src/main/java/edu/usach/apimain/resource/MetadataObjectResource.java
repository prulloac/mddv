package edu.usach.apimain.resource;

import edu.usach.apicommons.resource.AbstractResource;
import edu.usach.apicommons.service.IService;
import edu.usach.apimain.model.MetadataObject;
import edu.usach.apimain.service.IMetadataObjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(maxAge = 7200)
@RestController
@RequestMapping("/metadata-objects")
public class MetadataObjectResource extends AbstractResource<MetadataObject> {

	@Autowired
	IMetadataObjectService service;

	@Override
	protected IService getService() {
		return service;
	}
}
