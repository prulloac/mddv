package edu.usach.mddv.repository;

import edu.usach.mddv.model.UploadFile;
import org.springframework.data.repository.PagingAndSortingRepository;

import javax.transaction.Transactional;

@Transactional
public interface UploadFileRepository extends PagingAndSortingRepository<UploadFile,Long>{
}
