package edu.usach.apimain.service.impl;

import edu.usach.apicommons.errorhandling.ApiException;
import edu.usach.apicommons.service.EntityService;
import edu.usach.apimain.dao.AppUserDAO;
import edu.usach.apimain.errorhandling.ErrorCode;
import edu.usach.apimain.model.AppUser;
import edu.usach.apimain.service.IAppUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@Slf4j
public class AppUserService extends EntityService<AppUser> implements IAppUserService {

	@Autowired
	private AppUserDAO dao;

	@Override
	protected JpaRepository<AppUser, Long> getDao() {
		return dao;
	}

	@Override
	public AppUser validateCredentials(String usernameOrEmail, String password) throws ApiException {
		AppUser candidate = dao.findByUsernameIgnoreCaseOrEmailIgnoreCase(usernameOrEmail, usernameOrEmail);
		log.info("candidate: ", candidate);
		if (candidate == null || !password.equals(candidate.getPassword()))
			throw new ApiException(ErrorCode.INVALID_CREDENTIALS);
		return candidate;
	}
}
