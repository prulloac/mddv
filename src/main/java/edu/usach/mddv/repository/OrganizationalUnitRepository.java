package edu.usach.mddv.repository;

import edu.usach.mddv.model.OrganizationalUnit;
import org.springframework.data.repository.PagingAndSortingRepository;

import javax.transaction.Transactional;

@Transactional
public interface OrganizationalUnitRepository extends PagingAndSortingRepository<OrganizationalUnit,Long>{
}
