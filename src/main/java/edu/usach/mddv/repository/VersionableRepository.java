package edu.usach.mddv.repository;

import edu.usach.mddv.model.AbstractVersionable;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface VersionableRepository<T extends AbstractVersionable> extends DescriptableRepository<T>{
}
