package edu.usach.apicommons.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor(staticName = "of")
@NoArgsConstructor
public class SQLRelationDTO {
	private String sourceTable;
	private String sourceColumn;
	private String destinyTable;
	private String destinyColumn;
}
