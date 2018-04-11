package edu.usach.apimysql.service;

import edu.usach.apicommons.dto.ConnectionParamsDTO;
import edu.usach.apicommons.service.IExtractorService;
import org.springframework.http.ResponseEntity;

public interface IMySQLService extends IExtractorService {

	ResponseEntity connect(ConnectionParamsDTO connectionDTO);
}
