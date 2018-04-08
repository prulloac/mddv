package edu.usach.apimain.service.impl;

import edu.usach.apicommons.errorhandling.ApiException;
import edu.usach.apicommons.service.AbstractService;
import edu.usach.apimain.dao.AppUserDAO;
import edu.usach.apimain.errorhandling.ErrorCode;
import edu.usach.apimain.model.AppUser;
import edu.usach.apimain.service.IAppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AppUserService extends AbstractService<AppUser> implements IAppUserService {

	@Autowired
	AppUserDAO dao;

	@Override
	protected JpaRepository<AppUser, Long> getDao() {
		return dao;
	}

	@Override
	public AppUser validateCredentials(String usernameOrEmail, String password) throws ApiException {
		AppUser candidate = dao.findByUsernameIgnoreCaseOrEmailIgnoreCase(usernameOrEmail, usernameOrEmail);
		logger.info("candidate: ", candidate);
		if (candidate == null || !password.equals(candidate.getPassword()))
			throw new ApiException(ErrorCode.INVALID_CREDENTIALS);
		return candidate;
	}
}
