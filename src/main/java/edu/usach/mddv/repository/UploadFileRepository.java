package edu.usach.mddv.repository;

import edu.usach.mddv.model.UploadFile;
import org.springframework.data.repository.CrudRepository;

public interface UploadFileRepository extends CrudRepository<UploadFile, Long> {
}
