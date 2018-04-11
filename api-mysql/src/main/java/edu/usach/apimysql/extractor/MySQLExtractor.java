package edu.usach.apimysql.extractor;

import edu.usach.apicommons.dto.ConnectionParamsDTO;
import edu.usach.apicommons.extractor.AbstractExtractor;
import edu.usach.apicommons.extractor.AbstractSQLExtractor;
import edu.usach.apicommons.extractor.IExtractor;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLExtractor extends AbstractSQLExtractor implements IExtractor {

	private Connection connection;

	public MySQLExtractor(ConnectionParamsDTO connectionParams) {
		super(connectionParams);
	}

	@Override
	protected String jdbcUrl() {
		return "jdbc:mysql://" + connectionParams().getHost() + ":" + connectionParams().getPort() + "/" + connectionParams().getDatabase();
	}

	@Override
	public String databaseEngine() {
		return "MySQL";
	}

	@Override
	public String[] supportedVersions() {
		return new String[]{"5.7"};
	}

	@Override
	public JSONArray extractTables() {
		return null;
	}

	@Override
	public JSONArray extractColumns() {
		return null;
	}

	@Override
	public JSONArray extractRelations() {
		return null;
	}
}
