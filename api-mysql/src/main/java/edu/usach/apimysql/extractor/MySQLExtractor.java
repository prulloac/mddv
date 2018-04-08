package edu.usach.apimysql.extractor;

import edu.usach.apicommons.extractor.AbstractExtractor;
import edu.usach.apicommons.extractor.IExtractor;
import org.json.simple.JSONObject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLExtractor extends AbstractExtractor implements IExtractor {

	private Connection connection;

	public MySQLExtractor(String url, String username, String password) {
		super(url, username, password);
	}

	@Override
	public void connect() {
		try {
			String url = this.databaseParams.get("url");
			String username = this.databaseParams.get("username");
			String password = this.databaseParams.get("password");
			this.connection = DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		}
	}

	@Override
	public JSONObject extract() {
		return null;
	}

	@Override
	public String databaseType() {
		return "MySQL";
	}

	@Override
	public boolean isRelational() {
		return true;
	}

	@Override
	public String[] supportedVersions() {
		return new String[]{"5.7"};
	}
}
