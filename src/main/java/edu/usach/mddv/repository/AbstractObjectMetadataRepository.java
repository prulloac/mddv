package edu.usach.mddv.repository;

import edu.usach.mddv.model.AbstractObjectMetadata;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

@NoRepositoryBean
public interface AbstractObjectMetadataRepository<T extends AbstractObjectMetadata,ID extends Serializable> extends JpaRepository<T,ID> {
}
