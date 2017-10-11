package edu.usach.mddv.repository;

import edu.usach.mddv.model.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;

@Transactional
public interface RepositoryRepository extends JpaRepository<Repository,Long> {
}
