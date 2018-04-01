package edu.usach.apimain.resource;

import edu.usach.apicommons.service.IService;
import edu.usach.apimain.model.User;
import edu.usach.apimain.service.IUserService;
import edu.usach.apicommons.resource.AbstractResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@CrossOrigin(maxAge = 7200)
@RestController
@RequestMapping(value = "/users")
public class UserResource extends AbstractResource<User> {

	@Autowired
	HttpServletRequest httpServletRequest;
	@Autowired
	IUserService service;

	@Override
	protected HttpServletRequest getHttpServletRequest() {
		return httpServletRequest;
	}

	@Override
	protected IService getService() {
		return service;
	}

}
