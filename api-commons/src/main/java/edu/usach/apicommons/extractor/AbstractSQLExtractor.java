package edu.usach.apicommons.extractor;

import edu.usach.apicommons.dto.SQLColumnDTO;
import edu.usach.apicommons.dto.SQLExtractionDTO;
import edu.usach.apicommons.dto.SQLRelationDTO;
import edu.usach.apicommons.dto.SQLTableDTO;
import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Slf4j
public abstract class AbstractSQLExtractor extends AbstractExtractor implements SQLExtractor {

    protected Connection connection = null;

    protected abstract String jdbcUrl(Map<String, Object> connectionParamsDTO);

    protected SQLExtractionDTO extract(String database, DatabaseMetaData metaData) throws SQLException {
			String[] types = {"TABLE"};
			List<SQLTableDTO> tables = new ArrayList<>();
			List<SQLRelationDTO> relations = new ArrayList<>();
			ResultSet resultSet = null;
			resultSet = metaData.getTables(database, null, "%", types);
			log.info("Extracting tables");
			while (resultSet.next()) {
				SQLTableDTO table = new SQLTableDTO();
				table.setTableName(resultSet.getString("TABLE_NAME"));
				log.info("Extracted table: {}", table.getTableName());
				tables.add(table);
			}
			for(SQLTableDTO table: tables){
				List<SQLColumnDTO> columns = new ArrayList<>();
				resultSet = metaData.getColumns(database, null, table.getTableName(), "%");
				log.info("Extracting columns from table: {}", table.getTableName());
				while (resultSet.next()) {
					SQLColumnDTO column = new SQLColumnDTO();
					column.setColumnName(resultSet.getString("COLUMN_NAME"));
					column.setColumnType(resultSet.getString("TYPE_NAME"));
					column.setTableName(resultSet.getString("TABLE_NAME"));
					column.setPosition(resultSet.getInt("ORDINAL_POSITION"));
					column.setAutoIncrement(resultSet.getString("IS_AUTOINCREMENT"));
					column.setNullable(resultSet.getString("IS_NULLABLE"));
					column.setDefaultValue(resultSet.getString("COLUMN_DEF"));
					log.info("Extracted column: {}", column.getColumnName());
					columns.add(column);
				}
				table.setColumns(columns);
				resultSet = metaData.getImportedKeys(database, null, table.getTableName());
				log.info("Extracting foreign-keys from table: {}", table.getTableName());
				while (resultSet.next()) {
					SQLRelationDTO relation = new SQLRelationDTO();
					relation.setSourceTable(resultSet.getString("FKTABLE_NAME"));
					relation.setSourceColumn(resultSet.getString("FKCOLUMN_NAME"));
					relation.setDestinyTable(resultSet.getString("PKTABLE_NAME"));
					relation.setDestinyColumn(resultSet.getString("PKCOLUMN_NAME"));
					log.info("Extracted foreign-key: {}", relation.getSourceColumn());
					relations.add(relation);
				}
			}
			resultSet.close();
			log.info("Extraction complete");
			return SQLExtractionDTO.of(tables, relations, databaseType());
		}

}
