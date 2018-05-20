package edu.usach.apih2.extractor;

import edu.usach.apicommons.dto.SQLExtractionDTO;
import edu.usach.apicommons.extractor.AbstractSQLExtractor;
import edu.usach.apicommons.extractor.SQLExtractor;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONObject;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Map;

@SuppressWarnings({"unchecked", "unused"})
@Slf4j
public class H2Extractor extends AbstractSQLExtractor implements SQLExtractor {

	@Override
	public String databaseEngine() {
		return "H2";
	}

	@Override
	public String databaseType() {
		return "SQL";
	}

	@Override
	public String[] supportedVersions() {
		return new String[]{"1.4.196"};
	}

	@Override
	protected String jdbcUrl(Map<String, Object> connectionParams) {
		return "jdbc:h2:tcp://" + connectionParams.get("host") + ":" + connectionParams.get("port") + "/" + connectionParams.get("database");
	}

	@Override
	public JSONObject connectionParameters() {
		return null;
	}

	@Override
	public SQLExtractionDTO extract(Map<String, Object> connectionParams) {
		try {
			String database = (String) connectionParams.get("database");
			String username = (String) connectionParams.get("username");
			String password = (String) connectionParams.get("password");
			boolean isAuthRequired = (boolean) connectionParams.get("isAuthRequired");
			String[] types = {"TABLE"};
			Connection connection = null;
			String jdbcUrl = jdbcUrl(connectionParams);
			log.info("Attempting to connect to " + jdbcUrl);
			if (isAuthRequired)
				connection = DriverManager.getConnection(jdbcUrl, username, password);
			else
				connection = DriverManager.getConnection(jdbcUrl);
			log.info("Connected to " + jdbcUrl);
			DatabaseMetaData metaData = connection.getMetaData();
			SQLExtractionDTO extractionDTO = extract(database, metaData);
			extractionDTO.setRepositoryEngine(databaseEngine());
			extractionDTO.setRepositoryEngineVersion(metaData.getDatabaseProductVersion());
			return extractionDTO;
		} catch (SQLException e) {
			log.error(e.getMessage(), e);
		}
		return null;

	}
}
