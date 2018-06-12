package edu.usach.apimysql.extractor;

import edu.usach.apicommons.dto.SQLExtractionDTO;
import edu.usach.apicommons.extractor.AbstractSQLExtractor;
import edu.usach.apicommons.extractor.SQLExtractor;
import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@SuppressWarnings({"unchecked", "unused"})
@Slf4j
public class MySQLExtractor extends AbstractSQLExtractor implements SQLExtractor {

	@Override
	protected String jdbcUrl(Map<String, Object> connectionParams) {
		return "jdbc:mysql://" + connectionParams.get("host") + ":" + connectionParams.get("port") + "/" + connectionParams.get("database");
	}

	@Override
	public String databaseEngine() {
		return "MySQL";
	}

	@Override
	public String databaseType() {
		return "SQL";
	}

	@Override
	public String[] supportedVersions() {
		return new String[]{"5.7", "5.6", "5.5"};
	}

	@Override
	public List<Map<String, Object>> connectionParameters() {
		List<Map<String, Object>> parameters = new ArrayList<>();
		parameters.add(newConnectionParameter("text","host","Host"));
		parameters.add(newConnectionParameter("number","port","Puerto"));
		parameters.add(newConnectionParameter("text","database","Base de datos"));
		parameters.add(newConnectionParameter("text","username","Usuario"));
		parameters.add(newConnectionParameter("password","password","Contraseña"));
		return parameters;
	}

	@Override
	public SQLExtractionDTO extract(Map<String, Object> connectionParams) {
		try {
			String database = (String) connectionParams.get("database");
			String username = (String) connectionParams.get("username");
			String password = (String) connectionParams.get("password");
			Connection connection = null;
			String jdbcUrl = jdbcUrl(connectionParams);
			log.info("Attempting to connect to {}", jdbcUrl);
			connection = DriverManager.getConnection(jdbcUrl, username, password);
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
