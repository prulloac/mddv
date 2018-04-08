package edu.usach.apih2.service;

import edu.usach.apicommons.service.IExtractorService;
import edu.usach.apih2.dto.ConnectionDTO;
import org.springframework.http.ResponseEntity;

public interface IH2Service extends IExtractorService {

	ResponseEntity connect(ConnectionDTO connectionDTO);
}
