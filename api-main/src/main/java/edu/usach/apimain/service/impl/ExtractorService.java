package edu.usach.apimain.service.impl;

import edu.usach.apicommons.errorhandling.ApiException;
import edu.usach.apimain.dao.ExtractorDAO;
import edu.usach.apimain.errorhandling.ErrorCode;
import edu.usach.apimain.model.Extractor;
import edu.usach.apimain.service.IExtractorService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONArray;
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
import java.util.stream.Collectors;

@Service
@Transactional
public class ExtractorService implements IExtractorService {

	protected final Logger logger = LogManager.getLogger(this.getClass());

	@Autowired
	private ExtractorDAO dao;

	@Override
	public JSONArray getExtractors(String engine, String version) {
		List<Extractor> extractorList = dao.findAll();
		if (null!=engine) {
			extractorList = extractorList.stream().filter(x -> x.getSupportedEngine().equalsIgnoreCase(engine)).collect(Collectors.toList());
		}
		if (null!=version) {
			extractorList = extractorList.stream().filter(x -> x.getSupportedVersions().contains(version)).collect(Collectors.toList());
		}
		JSONArray engines = new JSONArray();
		for (Extractor extractor: extractorList) {
			Map<String, Object> data = new HashMap<>();
			String[] versions = extractor.getSupportedVersions().split(",");
			data.put("versions", versions);
			data.put("connectionParams", getExtractorParams(extractor.getSupportedEngine(), versions[0]).get("data"));
			data.put("engine", extractor.getSupportedEngine());
			data.put("name", extractor.getName());
			engines.add(data);
		}
		return engines;
	}

	private String getExtractorEntrypoint(String engine, String version) {
		Extractor extractor = dao.findBySupportedEngineAndSupportedVersionsContains(engine, version);
		return extractor.getApiUrl();
	}

	@Override
	public JSONObject callExtractor(String engine, String version, JSONObject connectionParams) {
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

	@Override
	public JSONObject getExtractorParams(String engine, String version) {
		String path = getExtractorEntrypoint(engine, version);
		logger.info("calling extractor at: {}", path);
		try {
			URL connectionUrl = new URL(path);
			HttpURLConnection con = (HttpURLConnection) connectionUrl.openConnection();
			con.setRequestMethod("GET");
			con.setDoInput(true);
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
