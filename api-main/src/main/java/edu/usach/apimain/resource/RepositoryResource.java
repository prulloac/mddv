package edu.usach.apimain.resource;

import edu.usach.apicommons.errorhandling.ErrorDTO;
import edu.usach.apicommons.resource.AbstractResource;
import edu.usach.apicommons.service.IService;
import edu.usach.apimain.model.Repository;
import edu.usach.apimain.service.IRepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static edu.usach.apicommons.util.Constants.OBJECT;

@CrossOrigin(maxAge = 7200)
@RestController
@RequestMapping("/repositories")
public class RepositoryResource extends AbstractResource<Repository> {

	@Autowired
	private IRepositoryService service;

	@Override
	protected IService<Repository> getService() {
		return service;
	}

	@Override
	@RequestMapping(
			method = {RequestMethod.GET}
	)
	public ResponseEntity getAll() {
		if (isAuthenticated())
			return super.getAll();
		else
			return responseUnauthorized(OBJECT, new ErrorDTO(httpServletRequest));
	}

}
