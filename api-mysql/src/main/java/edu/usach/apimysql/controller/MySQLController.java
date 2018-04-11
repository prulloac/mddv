package edu.usach.apimysql.controller;

import edu.usach.apicommons.dto.ConnectionParamsDTO;
import edu.usach.apimysql.service.IMySQLService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MySQLController {

	@Autowired
	private IMySQLService mySQLService;

	@RequestMapping(value = "/connect")
	public ResponseEntity connect(@RequestBody ConnectionParamsDTO connectionDTO) {
		return mySQLService.connect(connectionDTO);
	}

}
