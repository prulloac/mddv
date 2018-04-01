package edu.usach.apimain.dao;

import edu.usach.apimain.model.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface RepositoryDAO extends JpaRepository<Repository, Long> {
}
