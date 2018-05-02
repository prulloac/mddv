package edu.usach.apimain.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.usach.apicommons.errorhandling.ApiException;
import edu.usach.apimain.dao.ExtractorDAO;
import edu.usach.apimain.errorhandling.ErrorCode;
import edu.usach.apimain.model.Extractor;
import edu.usach.apimain.service.IExtractorService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class ExtractorService implements IExtractorService {

	protected final Logger logger = LogManager.getLogger(this.getClass());

	@Autowired
	private ExtractorDAO dao;

	@Override
	public Map<String, Object> getSupportedEngines() {
		List<Extractor> extractorList = dao.findAll();
		Map<String, Object> enginesWithVersions = new HashMap<>();
		extractorList.forEach(x -> enginesWithVersions.put(x.getSupportedEngine(), x.getSupportedVersions().split(",")));
		return enginesWithVersions;
	}

	@Override
	public String getExtractorEntrypoint(String engine, String version) {
		Extractor extractor = dao.findBySupportedEngineAndSupportedVersionsContains(engine, version);
		return extractor.getApiUrl().concat(extractor.getExtractorEntrypoint());
	}

	@Override
	public JSONObject callExtractor(String engine, String version, JSONObject connectionParams) throws ApiException {
		String path = getExtractorEntrypoint(engine, version);
		String data = connectionParams.toJSONString();
		logger.info("calling extractor at: {}", path);
		logger.info("post data: {}", data);
		try {
			URL connectionUrl = new URL(path);
			HttpURLConnection con = (HttpURLConnection) connectionUrl.openConnection();
			con.setRequestMethod("POST");
			con.setDoInput(true);
			con.setDoOutput(true);
			con.setRequestProperty("Content-Type", "application/json");
			OutputStream os = con.getOutputStream();
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
			writer.write(data);
			writer.flush();
			writer.close();
			con.connect();
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String inputLine;
			JSONObject object = null;
			JSONParser parser = new JSONParser();
			while ((inputLine = in.readLine()) != null) {
				object = (JSONObject) parser.parse(inputLine);
			}
			return object;
		} catch (IOException | ParseException e) {
			logger.error(e.getMessage(), e);
			throw new ApiException(ErrorCode.ERROR_CONNECTING_EXTRACTOR);
		}
	}
}
