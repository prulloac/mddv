package edu.usach.apimain.controller;

import edu.usach.apicommons.controller.EntityController;
import edu.usach.apicommons.service.IEntityService;
import edu.usach.apimain.model.BusinessObject;
import edu.usach.apimain.service.IBusinessObjectService;
import edu.usach.apimain.util.BusinessTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.stream.Collectors;

@CrossOrigin(maxAge = 7200)
@RestController
@RequestMapping("/business-objects")
public class BusinessObjectController extends EntityController<BusinessObject> {

	@Autowired
	private IBusinessObjectService service;

	@Override
	protected IEntityService<BusinessObject> getService() {
		return service;
	}

	@RequestMapping(value = "/types", method = RequestMethod.GET)
	public ResponseEntity<Object> getTypes() {
		return response(Arrays.stream(BusinessTypes.values()).map(BusinessTypes::getTranslation).collect(Collectors.toList()));
	}
}
