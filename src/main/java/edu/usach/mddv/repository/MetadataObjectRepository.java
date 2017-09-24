package edu.usach.mddv.repository;

import edu.usach.mddv.model.MetadataObject;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.io.Serializable;

@NoRepositoryBean
public interface MetadataObjectRepository<T extends MetadataObject,ID extends Serializable> extends PagingAndSortingRepository<T,ID>{
}
