package edu.usach.mddv.repository;


import edu.usach.mddv.model.User;
import org.springframework.data.repository.PagingAndSortingRepository;

import javax.transaction.Transactional;

@Transactional
public interface UserRepository extends PagingAndSortingRepository<User,Long>{

}
