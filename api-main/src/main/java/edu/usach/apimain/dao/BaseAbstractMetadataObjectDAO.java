package edu.usach.apimain.dao;

import edu.usach.apimain.model.AbstractMetadataObject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface BaseAbstractMetadataObjectDAO<T extends AbstractMetadataObject> extends JpaRepository<T, Long> {
}
