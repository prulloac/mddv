package edu.usach.apimain.dao;

import edu.usach.apimain.model.ConnectionParameter;
import edu.usach.apimain.model.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface ConnectionParamDAO extends JpaRepository<ConnectionParameter, Long> {
	List<ConnectionParameter> findByRepository(Repository repository);
}
