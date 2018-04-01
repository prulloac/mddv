package edu.usach.apimain.service.impl;

import edu.usach.apicommons.service.AbstractService;
import edu.usach.apimain.dao.UserDAO;
import edu.usach.apimain.model.User;
import edu.usach.apimain.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserService extends AbstractService<User> implements IUserService{

	@Autowired
	UserDAO dao;

	@Override
	protected PagingAndSortingRepository<User, Long> getDao() {
		return dao;
	}
}
