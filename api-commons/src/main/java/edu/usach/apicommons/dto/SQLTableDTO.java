package edu.usach.apicommons.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor(staticName = "of")
@NoArgsConstructor
public class SQLTableDTO {
	private String tableName;
	private List<SQLColumnDTO> columns;
}
