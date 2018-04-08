package edu.usach.apimysql.service;

import edu.usach.apicommons.service.IExtractorService;
import edu.usach.apimysql.dto.ConnectionDTO;
import org.springframework.http.ResponseEntity;

public interface IMySQLService extends IExtractorService {

	ResponseEntity connect(ConnectionDTO connectionDTO);
}
