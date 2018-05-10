package edu.usach.apimysql.extractor;

import edu.usach.apicommons.extractor.AbstractSQLExtractor;
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
			List<MySQLTable> tables = new ArrayList<>();
			List<MySQLRelation> relations = new ArrayList<>();
			ResultSet resultSet = null;
			resultSet = connection.getMetaData().getTables(database, null, "%", types);
			logger.info("Extracting tables");
			while (resultSet.next()) {
				MySQLTable table = new MySQLTable();
				table.setTableName(resultSet.getString("TABLE_NAME"));
				tables.add(table);
			}
			resultSet.close();
			for (MySQLTable table : tables) {
				logger.info("Extracting columns from " + table.getTableName());
				resultSet = connection.getMetaData().getColumns(database, null, table.getTableName(), "%");
				while (resultSet.next()) {
					MySQLColumn column = new MySQLColumn();
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
					MySQLRelation relation = new MySQLRelation();
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


	private static class MySQLTable {
		private String tableName;
		private List<MySQLColumn> columns;

		public MySQLTable() {
			this.columns = new ArrayList<>();
		}

		public String getTableName() {
			return tableName;
		}

		public void setTableName(String tableName) {
			this.tableName = tableName;
		}

		public List<MySQLColumn> getColumns() {
			return columns;
		}

		public void setColumns(List<MySQLColumn> columns) {
			this.columns = columns;
		}
	}

	private static class MySQLColumn {
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

	private static class MySQLRelation {
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
