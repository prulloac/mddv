package edu.usach.apicommons.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@RequiredArgsConstructor(staticName = "of")
@AllArgsConstructor(staticName = "of")
public class SQLExtractionDTO {
	@NonNull private List<SQLTableDTO> tables;
	@NonNull private List<SQLRelationDTO> relations;
	@NonNull private String repositoryType;
	private String repositoryEngine;
	private String repositoryEngineVersion;
}
