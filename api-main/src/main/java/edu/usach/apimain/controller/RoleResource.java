package edu.usach.apimain.controller;

import edu.usach.apicommons.controller.EntityController;
import edu.usach.apicommons.service.IEntityService;
import edu.usach.apimain.model.Role;
import edu.usach.apimain.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(maxAge = 7200)
@RestController
@RequestMapping("/roles")
public class RoleResource extends EntityController<Role> {

	@Autowired
	private IRoleService service;

	@Override
	protected IEntityService<Role> getService() {
		return service;
	}
}
