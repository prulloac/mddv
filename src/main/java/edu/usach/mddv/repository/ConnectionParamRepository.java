package edu.usach.mddv.repository;

import edu.usach.mddv.model.ConnectionParam;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;

@Transactional
public interface ConnectionParamRepository extends JpaRepository<ConnectionParam, Long>{
}
