package edu.usach.mddv.repository;

import edu.usach.mddv.model.User;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

@Transactional
public interface UserRepository extends CrudRepository<User,Long> {
}
