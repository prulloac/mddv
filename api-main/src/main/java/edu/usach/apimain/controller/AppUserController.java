package edu.usach.apimain.controller;

import edu.usach.apicommons.controller.EntityController;
import edu.usach.apicommons.errorhandling.ApiException;
import edu.usach.apicommons.errorhandling.ErrorDTO;
import edu.usach.apicommons.service.IEntityService;
import edu.usach.apicommons.util.SecurityUtils;
import edu.usach.apimain.dto.UserCredentialsDTO;
import edu.usach.apimain.dto.UserTokenDataDTO;
import edu.usach.apimain.model.AppUser;
import edu.usach.apimain.service.IAppUserService;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static edu.usach.apicommons.util.Constants.OBJECT;
import static edu.usach.apicommons.util.SecurityUtils.HEADER_STRING;
import static edu.usach.apicommons.util.SecurityUtils.TOKEN_PREFIX;

@CrossOrigin(maxAge = 7200, exposedHeaders = {"Authorization"})
@RestController
@RequestMapping("/users")
@Slf4j
public class AppUserController extends EntityController<AppUser> {

	@Autowired
	private IAppUserService service;

	private ResponseEntity<Object> validateCredentials(UserCredentialsDTO credentials) {
		AppUser user = service.validateCredentials(credentials.getUsernameOrEmail(), credentials.getPassword());
		String token = SecurityUtils.tokenize(new UserTokenDataDTO(user), "authenticated");
		HttpHeaders headers = new HttpHeaders();
		headers.add(HEADER_STRING, TOKEN_PREFIX + token);
		return new ResponseEntity<>(new UserTokenDataDTO(user), headers, HttpStatus.OK);
	}

	@Override
	protected IEntityService<AppUser> getService() {
		return service;
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ResponseEntity<Object> login(@RequestBody UserCredentialsDTO credentials) throws ApiException {
		log.info("username: {}", credentials.getUsernameOrEmail());
		return validateCredentials(credentials);
	}

}
