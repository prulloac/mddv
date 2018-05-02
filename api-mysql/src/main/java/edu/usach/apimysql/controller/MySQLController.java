package edu.usach.apimysql.controller;

import edu.usach.apicommons.controller.ExtractorController;
import edu.usach.apicommons.dto.ConnectionParamsDTO;
import edu.usach.apicommons.errorhandling.ApiException;
import edu.usach.apicommons.errorhandling.ErrorDTO;
import edu.usach.apicommons.service.IExtractorService;
import edu.usach.apimysql.extractor.MySQLExtractor;
import edu.usach.apimysql.service.IMySQLService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static edu.usach.apicommons.util.Constants.ARRAY;

@RestController
@CrossOrigin(value = "*", maxAge = 7200)
public class MySQLController extends ExtractorController<MySQLExtractor> {

	@Autowired
	private IMySQLService mySQLService;

	@Override
	protected IExtractorService<MySQLExtractor> getService() {
		return mySQLService;
	}

	@Override
	@RequestMapping(value = "/extract", method = RequestMethod.POST)
	public ResponseEntity<Object> extract(@RequestBody ConnectionParamsDTO connectionParamsDTO) {
		try {
			return response(mySQLService.extract(connectionParamsDTO));
		} catch (ApiException e) {
			logger.error(e.getMessage(), e);
			return responseUnauthorized(ARRAY, new ErrorDTO(e, httpServletRequest));
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return responseInternalServerError(ARRAY, new ErrorDTO(httpServletRequest));
		}
	}

}
