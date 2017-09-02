package edu.usach.mddv.repository;

import edu.usach.mddv.model.AbstractDescriptable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface DescriptableRepository<T extends AbstractDescriptable> extends CrudRepository<T, Long> {
}
