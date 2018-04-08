package edu.usach.apimain.controller;

import edu.usach.apicommons.controller.EntityController;
import edu.usach.apicommons.service.IEntityService;
import edu.usach.apimain.model.BusinessObject;
import edu.usach.apimain.service.IBusinessObjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(maxAge = 7200)
@RestController
@RequestMapping("/business-objects")
public class BusinessObjectResource extends EntityController<BusinessObject> {

	@Autowired
	private IBusinessObjectService service;

	@Override
	protected IEntityService<BusinessObject> getService() {
		return service;
	}
}
