package edu.usach.apimain.resource;

import edu.usach.apicommons.resource.AbstractResource;
import edu.usach.apicommons.service.IService;
import edu.usach.apimain.model.ConnectionParameter;
import edu.usach.apimain.service.IConnectionParameterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@CrossOrigin(maxAge = 7200)
@RestController
@RequestMapping("/connection-parameters")
public class ConnectionParameterResource extends AbstractResource<ConnectionParameter> {

	@Autowired
	HttpServletRequest httpServletRequest;

	@Autowired
	IConnectionParameterService service;

	@Override
	protected HttpServletRequest getHttpServletRequest() {
		return httpServletRequest;
	}

	@Override
	protected IService getService() {
		return service;
	}
}
