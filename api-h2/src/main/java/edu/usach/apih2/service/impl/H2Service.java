package edu.usach.apih2.service.impl;

import edu.usach.apicommons.service.ExtractorService;
import edu.usach.apih2.dto.ConnectionDTO;
import edu.usach.apih2.extractor.H2Extractor;
import edu.usach.apih2.service.IH2Service;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class H2Service extends ExtractorService implements IH2Service {

  @Override
  public ResponseEntity connect(ConnectionDTO connectionDTO) {
    H2Extractor extractor = new H2Extractor(connectionDTO.getUrl(), connectionDTO.getUsername(),
        connectionDTO.getPassword());
    extractor.connect();
    return new ResponseEntity<>(true, HttpStatus.OK);
  }
}
