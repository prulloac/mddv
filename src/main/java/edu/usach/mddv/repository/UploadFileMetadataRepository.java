package edu.usach.mddv.repository;

import edu.usach.mddv.model.UploadFileMetadata;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;

@Transactional
public interface UploadFileMetadataRepository extends JpaRepository<UploadFileMetadata,Long>{
}
