package edu.usach.apimain.resource;

import edu.usach.apicommons.errorhandling.ErrorDTO;
import edu.usach.apicommons.resource.AbstractResource;
import edu.usach.apicommons.service.IService;
import edu.usach.apicommons.util.Constants;
import edu.usach.apicommons.util.SecurityUtils;
import edu.usach.apimain.model.OrganizationalUnit;
import edu.usach.apimain.service.IOrganizationalUnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

import static edu.usach.apicommons.util.Constants.OBJECT;
import static edu.usach.apicommons.util.SecurityUtils.HEADER_STRING;

@CrossOrigin(maxAge = 7200)
@RestController
@RequestMapping("/organizational-units")
public class OrganizationalUnitResource extends AbstractResource<OrganizationalUnit> {

	@Autowired
	IOrganizationalUnitService service;

	@Override
	protected IService getService() {
		return service;
	}

}
