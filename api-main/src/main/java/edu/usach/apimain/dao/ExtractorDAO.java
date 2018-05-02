package edu.usach.apimain.dao;

import edu.usach.apimain.model.Extractor;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;

@Transactional
public interface ExtractorDAO extends JpaRepository<Extractor, Long> {

	Extractor findBySupportedEngineAndSupportedVersionsContains(String engine, String version);
}
