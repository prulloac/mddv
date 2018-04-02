package edu.usach.apimain.service;

import edu.usach.apicommons.errorhandling.ApiException;
import edu.usach.apicommons.service.IService;
import edu.usach.apimain.model.User;

public interface IUserService extends IService<User> {

	User validateCredentials(String usernameOrEmail, String password) throws ApiException;

}
