package edu.usach.mddv.repository;

import edu.usach.mddv.model.BusinessObject;
import org.springframework.data.repository.CrudRepository;

public interface BusinessObjectRepository extends CrudRepository<BusinessObject, Long> {
}
