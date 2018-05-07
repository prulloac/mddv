package edu.usach.apih2.service.impl;

import edu.usach.apicommons.service.ExtractorService;
import edu.usach.apih2.extractor.H2Extractor;
import edu.usach.apih2.service.IH2Service;
import org.springframework.stereotype.Service;

@Service
public class H2Service extends ExtractorService<H2Extractor> implements IH2Service {

	@Override
	protected H2Extractor buildExtractor() {
		return new H2Extractor();
	}
}
