package edu.usach.apih2.service;

import edu.usach.apicommons.dto.ConnectionParamsDTO;
import edu.usach.apicommons.service.IExtractorService;
import org.springframework.http.ResponseEntity;

public interface IH2Service extends IExtractorService {

	ResponseEntity connect(ConnectionParamsDTO connectionDTO);
}
