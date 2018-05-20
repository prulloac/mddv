package edu.usach.apipostgres.extractor;

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
public class PostgresExtractor extends AbstractSQLExtractor implements SQLExtractor {

	@Override
	protected String jdbcUrl(Map<String, Object> connectionParams) {
		return "jdbc:postgresql://" + connectionParams.get("host") + ":" + connectionParams.get("port") + "/" + connectionParams.get("database");
	}

	@Override
	public String databaseEngine() {
		return "Postgres";
	}

	@Override
	public String databaseType() {
		return "SQL";
	}

	@Override
	public String[] supportedVersions() {
		return new String[]{"10.4", "9.6", "9.5", "9.4", "8.2"};
	}

	@Override
	public JSONObject connectionParameters() {
		JSONObject parameters = new JSONObject();
		parameters.put("database","string");
		parameters.put("username","string");
		parameters.put("password","string");
		parameters.put("host","string");
		parameters.put("port","int");
		parameters.put("isAuthRequired","boolean");
		return parameters;
	}

	@Override
	public SQLExtractionDTO extract(Map<String, Object> connectionParams) {
		try {
			String database = (String) connectionParams.get("database");
			String username = (String) connectionParams.get("username");
			String password = (String) connectionParams.get("password");
			boolean isAuthRequired = (boolean) connectionParams.get("isAuthRequired");
			Connection connection = null;
			String jdbcUrl = jdbcUrl(connectionParams);
			log.info("Attempting to connect to {}", jdbcUrl);
			if (isAuthRequired)
				connection = DriverManager.getConnection(jdbcUrl, username, password);
			else
				connection = DriverManager.getConnection(jdbcUrl);
			log.info("Connected to {}", jdbcUrl);
			DatabaseMetaData metaData = connection.getMetaData();
			SQLExtractionDTO extractionDTO = extract(database, metaData);
			extractionDTO.setRepositoryEngine(databaseEngine());
			extractionDTO.setRepositoryEngineVersion(metaData.getDatabaseMajorVersion()+"."+metaData.getDatabaseMinorVersion());
			return extractionDTO;
		} catch (SQLException e) {
			log.error(e.getMessage(), e);
		}
		return null;
	}

}
