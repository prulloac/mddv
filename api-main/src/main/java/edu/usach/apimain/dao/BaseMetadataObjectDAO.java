package edu.usach.apimain.dao;

import edu.usach.apimain.model.MetadataObject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface BaseMetadataObjectDAO<T extends MetadataObject> extends JpaRepository<T, Long> {
}
