package edu.usach.apimain.controller;

import edu.usach.apicommons.controller.EntityController;
import edu.usach.apicommons.service.IEntityService;
import edu.usach.apimain.model.TechnicalObject;
import edu.usach.apimain.service.ITechnicalObjectService;
import edu.usach.apimain.util.BusinessTypes;
import edu.usach.apimain.util.TechnicalTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.stream.Collectors;

@CrossOrigin(maxAge = 7200)
@RestController
@RequestMapping("/technical-objects")
public class TechnicalObjectController extends EntityController<TechnicalObject> {

	@Autowired
	private ITechnicalObjectService service;

	@Override
	protected IEntityService<TechnicalObject> getService() {
		return service;
	}

	@RequestMapping(value = "/types", method = RequestMethod.GET)
	public ResponseEntity<Object> getTypes() {
		return response(Arrays.stream(TechnicalTypes.values()).map(TechnicalTypes::getTranslation).collect(Collectors.toList()));
	}

	@RequestMapping(value = "/repositories", method = RequestMethod.GET)
	public ResponseEntity<Object> getRepositories() {
		return response(service.getRepositories());
	}

	@RequestMapping(value = "/children", method = RequestMethod.GET)
	public ResponseEntity<Object> getChildrenObjects(@RequestParam("parentId") Long id) {
		return response(service.getChildrenObjects(id));
	}
}
