package edu.usach.apimain.service;

import edu.usach.apicommons.errorhandling.ApiException;
import edu.usach.apicommons.service.IEntityService;
import edu.usach.apimain.model.AppUser;

public interface IAppUserService extends IEntityService<AppUser> {

	AppUser validateCredentials(String usernameOrEmail, String password) throws ApiException;

}
