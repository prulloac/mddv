package edu.usach.mddv.repository;

import edu.usach.mddv.model.UploadFileInstance;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;

@Transactional
public interface UploadFileInstanceRepository extends JpaRepository<UploadFileInstance, Long>{
}
