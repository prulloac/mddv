package edu.usach.apimongo.controller;

import edu.usach.apicommons.controller.AbstractController;
import edu.usach.apicommons.controller.ExtractorController;
import edu.usach.apicommons.dto.ConnectionParamsDTO;
import edu.usach.apicommons.errorhandling.ApiException;
import edu.usach.apicommons.errorhandling.ErrorDTO;
import edu.usach.apicommons.service.IExtractorService;
import edu.usach.apicommons.util.Constants;
import edu.usach.apimongo.dto.MongoConnectionParamsDTO;
import edu.usach.apimongo.extractor.MongoExtractor;
import edu.usach.apimongo.service.IMongoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class MongoController extends AbstractController {

	@Autowired
	private IMongoService service;

	@RequestMapping(
			method = {RequestMethod.POST},
			value = {"/extract"}
	)
	public ResponseEntity extract(@RequestBody MongoConnectionParamsDTO connectionParamsDTO) {
		try {
			return this.response(service.extract(connectionParamsDTO));
		} catch (ApiException var3) {
			this.logger.error(var3.getMessage(), var3);
			return this.responseNotFound(Constants.OBJECT, new ErrorDTO(var3, this.httpServletRequest));
		} catch (Exception var4) {
			this.logger.error(var4.getMessage(), var4);
			return this.responseInternalServerError(Constants.OBJECT, new ErrorDTO(this.httpServletRequest));
		}
	}

}
