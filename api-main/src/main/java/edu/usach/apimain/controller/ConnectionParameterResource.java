package edu.usach.apimain.controller;

import edu.usach.apicommons.controller.EntityController;
import edu.usach.apicommons.service.IEntityService;
import edu.usach.apimain.model.ConnectionParameter;
import edu.usach.apimain.service.IConnectionParameterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(maxAge = 7200)
@RestController
@RequestMapping("/connection-parameters")
public class ConnectionParameterResource extends EntityController<ConnectionParameter> {

	@Autowired
	private IConnectionParameterService service;

	@Override
	protected IEntityService<ConnectionParameter> getService() {
		return service;
	}
}
