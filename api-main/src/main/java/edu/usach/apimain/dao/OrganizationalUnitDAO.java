package edu.usach.apimain.dao;

import edu.usach.apimain.model.OrganizationalUnit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface OrganizationalUnitDAO extends JpaRepository<OrganizationalUnit, Long> {
}
