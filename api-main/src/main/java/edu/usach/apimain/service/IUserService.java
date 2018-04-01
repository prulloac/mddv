package edu.usach.apimain.service;

import edu.usach.apicommons.service.IService;
import edu.usach.apimain.errors.ErrorMessage;
import edu.usach.apimain.model.User;
import edu.usach.apimain.dao.UserDAO;
import edu.usach.apicommons.errorhandling.ApiException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

public interface IUserService extends IService<User> {
}
