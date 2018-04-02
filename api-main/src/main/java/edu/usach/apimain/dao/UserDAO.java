package edu.usach.apimain.dao;

import edu.usach.apimain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface UserDAO extends JpaRepository<User, Long> {

	@Query("select u from User u where UPPER(u.username) = UPPER(?1) or UPPER(u.email) = UPPER(?1)")
	User findByUsernameOrEmail(String usernameOrEmail);

}
