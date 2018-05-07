package edu.usach.apih2.extractor;

import edu.usach.apicommons.extractor.AbstractSQLExtractor;
import edu.usach.apicommons.extractor.IExtractor;
import edu.usach.apicommons.extractor.SQLExtractor;
import org.json.simple.JSONObject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@SuppressWarnings({"unchecked", "unused"})
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
	public JSONObject extract(Map<String, Object> connectionParams) {
		try {
			String database = (String) connectionParams.get("database");
			String username = (String) connectionParams.get("username");
			String password = (String) connectionParams.get("password");
			boolean isAuthRequired = (boolean) connectionParams.get("isAuthRequired");
			String[] types = {"TABLE"};
			Connection connection = null;
			String jdbcUrl = jdbcUrl(connectionParams);
			logger.info("Attempting to connect to " + jdbcUrl);
			if (isAuthRequired)
				connection = DriverManager.getConnection(jdbcUrl, username, password);
			else
				connection = DriverManager.getConnection(jdbcUrl);
			logger.info("Connected to " + jdbcUrl);
			List<H2Table> tables = new ArrayList<>();
			List<H2Relation> relations = new ArrayList<>();
			ResultSet resultSet = null;
			resultSet = connection.getMetaData().getTables(database, null, "%", types);
			logger.info("Extracting tables");
			while (resultSet.next()) {
				H2Table table = new H2Table();
				table.setTableName(resultSet.getString("TABLE_NAME"));
				tables.add(table);
			}
			resultSet.close();
			for (H2Table table : tables) {
				logger.info("Extracting columns from " + table.getTableName());
				resultSet = connection.getMetaData().getColumns(database, null, table.getTableName(), "%");
				while (resultSet.next()) {
					H2Column column = new H2Column();
					column.setColumnName(resultSet.getString("COLUMN_NAME"));
					column.setColumnType(resultSet.getString("TYPE_NAME"));
					column.setTableName(resultSet.getString("TABLE_NAME"));
					column.setPosition(resultSet.getInt("ORDINAL_POSITION"));
					column.setAutoIncrement(resultSet.getString("IS_AUTOINCREMENT"));
					column.setNullable(resultSet.getString("IS_NULLABLE"));
					column.setDefaultValue(resultSet.getString("COLUMN_DEF"));
					table.getColumns().add(column);
				}
				resultSet.close();
				logger.info("Extracting foreign keys from " + table.getTableName());
				resultSet = connection.getMetaData().getImportedKeys(database, null, table.tableName);
				while (resultSet.next()) {
					H2Relation relation = new H2Relation();
					relation.setSourceTable(resultSet.getString("FKTABLE_NAME"));
					relation.setSourceColumn(resultSet.getString("FKCOLUMN_NAME"));
					relation.setDestinyTable(resultSet.getString("PKTABLE_NAME"));
					relation.setDestinyColumn(resultSet.getString("PKCOLUMN_NAME"));
					relations.add(relation);
				}
				resultSet.close();
			}
			JSONObject object = new JSONObject();
			object.put("tables", tables);
			object.put("relations", relations);
			logger.info("Metadata extraction complete");
			return object;
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		}
		return null;

	}

	private static class H2Table {
		private String tableName;
		private List<H2Column> columns;

		public H2Table() {
			this.columns = new ArrayList<>();
		}

		public String getTableName() {
			return tableName;
		}

		public void setTableName(String tableName) {
			this.tableName = tableName;
		}

		public List<H2Column> getColumns() {
			return columns;
		}

		public void setColumns(List<H2Column> columns) {
			this.columns = columns;
		}
	}

	private static class H2Column {
		private String columnName;
		private String columnType;
		private String defaultValue;
		private Integer position;
		private String tableName;
		private String autoIncrement;
		private String nullable;

		public String getAutoIncrement() {
			return autoIncrement;
		}

		public void setAutoIncrement(String autoIncrement) {
			this.autoIncrement = autoIncrement;
		}

		public String getNullable() {
			return nullable;
		}

		public void setNullable(String nullable) {
			this.nullable = nullable;
		}

		public String getColumnName() {
			return columnName;
		}

		public void setColumnName(String columnName) {
			this.columnName = columnName;
		}

		public String getColumnType() {
			return columnType;
		}

		public void setColumnType(String columnType) {
			this.columnType = columnType;
		}

		public String getDefaultValue() {
			return defaultValue;
		}

		public void setDefaultValue(String defaultValue) {
			this.defaultValue = defaultValue;
		}

		public Integer getPosition() {
			return position;
		}

		public void setPosition(Integer position) {
			this.position = position;
		}

		public String getTableName() {
			return tableName;
		}

		public void setTableName(String tableName) {
			this.tableName = tableName;
		}

	}

	private static class H2Relation {
		private String sourceTable;
		private String sourceColumn;
		private String destinyTable;
		private String destinyColumn;

		public String getSourceTable() {
			return sourceTable;
		}

		public void setSourceTable(String sourceTable) {
			this.sourceTable = sourceTable;
		}

		public String getSourceColumn() {
			return sourceColumn;
		}

		public void setSourceColumn(String sourceColumn) {
			this.sourceColumn = sourceColumn;
		}

		public String getDestinyTable() {
			return destinyTable;
		}

		public void setDestinyTable(String destinyTable) {
			this.destinyTable = destinyTable;
		}

		public String getDestinyColumn() {
			return destinyColumn;
		}

		public void setDestinyColumn(String getDestinyColumn) {
			this.destinyColumn = getDestinyColumn;
		}
	}

}
