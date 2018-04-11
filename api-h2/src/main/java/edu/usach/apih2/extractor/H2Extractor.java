package edu.usach.apih2.extractor;

import edu.usach.apicommons.dto.ConnectionParamsDTO;
import edu.usach.apicommons.extractor.AbstractExtractor;
import edu.usach.apicommons.extractor.AbstractSQLExtractor;
import edu.usach.apicommons.extractor.IExtractor;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class H2Extractor extends AbstractSQLExtractor implements IExtractor {

	public H2Extractor(ConnectionParamsDTO connectionParams) {
		super(connectionParams);
	}

	@Override
	protected String jdbcUrl() {
		return "jdbc:h2:tcp://" + connectionParams().getHost() + ":" + connectionParams().getHost() + "/" + connectionParams().getDatabase();
	}

	@Override
	public String databaseEngine() {
		return "H2";
	}

	@Override
	public String[] supportedVersions() {
		return new String[]{"1.4.196"};
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
