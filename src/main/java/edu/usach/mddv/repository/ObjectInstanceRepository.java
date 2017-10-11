package edu.usach.mddv.repository;

import edu.usach.mddv.model.ObjectInstance;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;

@Transactional
public interface ObjectInstanceRepository extends JpaRepository<ObjectInstance,Long>{
}
