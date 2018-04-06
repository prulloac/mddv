package edu.usach.apimain.dao;

import edu.usach.apimain.model.ConnectionParameter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface ConnectionParamDAO extends JpaRepository<ConnectionParameter, Long> {
}