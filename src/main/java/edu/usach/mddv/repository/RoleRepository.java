package edu.usach.mddv.repository;

import edu.usach.mddv.model.Role;
import org.springframework.data.repository.PagingAndSortingRepository;

import javax.transaction.Transactional;

@Transactional
public interface RoleRepository extends PagingAndSortingRepository<Role,Long>{
}
