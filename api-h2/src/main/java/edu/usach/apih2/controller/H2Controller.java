package edu.usach.apih2.controller;

import edu.usach.apih2.dto.ConnectionDTO;
import edu.usach.apih2.service.IH2Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class H2Controller {

	@Autowired
	private IH2Service mySQLService;

	@RequestMapping(value = "/connect")
	public ResponseEntity connect(@RequestBody ConnectionDTO connectionDTO) {
		return mySQLService.connect(connectionDTO);
	}

}
