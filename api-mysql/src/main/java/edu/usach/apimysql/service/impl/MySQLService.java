package edu.usach.apimysql.service.impl;

import edu.usach.apicommons.service.ExtractorService;
import edu.usach.apimysql.dto.ConnectionDTO;
import edu.usach.apimysql.extractor.MySQLExtractor;
import edu.usach.apimysql.service.IMySQLService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class MySQLService extends ExtractorService implements IMySQLService {

	@Override
	public ResponseEntity connect(ConnectionDTO connectionDTO) {
		MySQLExtractor extractor = new MySQLExtractor(connectionDTO.getUrl(), connectionDTO.getUsername(), connectionDTO.getPassword());
		extractor.connect();
		return new ResponseEntity<>(true, HttpStatus.OK);
	}
}
