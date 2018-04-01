package edu.usach.apimain.dao;

import edu.usach.apimain.model.Document;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface DocumentDAO extends JpaRepository<Document, Long> {
}
