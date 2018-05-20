package edu.usach.apicommons.dto;

import lombok.Data;

@Data
public class SQLColumnDTO {
	private String columnName;
	private String columnType;
	private String defaultValue;
	private Integer position;
	private String tableName;
	private String autoIncrement;
	private String nullable;
}
