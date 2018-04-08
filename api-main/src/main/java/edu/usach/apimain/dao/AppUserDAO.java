package edu.usach.apimain.dao;

import edu.usach.apimain.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface AppUserDAO extends JpaRepository<AppUser, Long> {

	AppUser findByUsernameIgnoreCaseOrEmailIgnoreCase(String username, String email);

}
