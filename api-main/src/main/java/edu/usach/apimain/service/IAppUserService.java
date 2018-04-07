package edu.usach.apimain.service;

import edu.usach.apicommons.errorhandling.ApiException;
import edu.usach.apicommons.service.IService;
import edu.usach.apimain.model.AppUser;

public interface IAppUserService extends IService<AppUser> {

	AppUser validateCredentials(String usernameOrEmail, String password) throws ApiException;

}
