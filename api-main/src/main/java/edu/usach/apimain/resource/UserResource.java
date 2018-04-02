package edu.usach.apimain.resource;

import edu.usach.apicommons.errorhandling.ApiException;
import edu.usach.apicommons.service.IService;
import edu.usach.apimain.dto.UserCredentialsDTO;
import edu.usach.apimain.model.User;
import edu.usach.apimain.service.IUserService;
import edu.usach.apicommons.resource.AbstractResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@CrossOrigin(maxAge = 7200)
@RestController
@RequestMapping("/users")
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

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ResponseEntity login(@RequestBody UserCredentialsDTO credentials) {
		try {
			return new ResponseEntity<>(
					service.validateCredentials(credentials.getUsernameOrEmail(), credentials.getPassword()),
					HttpStatus.OK
			);
		} catch (ApiException e) {
			return errorEntity(e, HttpStatus.UNAUTHORIZED);
		} catch (Exception e) {
			return errorEntity(e);
		}
	}

}
