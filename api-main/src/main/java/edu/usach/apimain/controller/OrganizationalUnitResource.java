package edu.usach.apimain.controller;

import edu.usach.apicommons.controller.EntityController;
import edu.usach.apicommons.service.IEntityService;
import edu.usach.apimain.model.OrganizationalUnit;
import edu.usach.apimain.service.IOrganizationalUnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(maxAge = 7200)
@RestController
@RequestMapping("/organizational-units")
public class OrganizationalUnitResource extends EntityController<OrganizationalUnit> {

	@Autowired
	private IOrganizationalUnitService service;

	@Override
	protected IEntityService<OrganizationalUnit> getService() {
		return service;
	}

}
