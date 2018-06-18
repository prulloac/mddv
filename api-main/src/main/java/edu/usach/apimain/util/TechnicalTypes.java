package edu.usach.apimain.util;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TechnicalTypes {
	REPOSITORY("Repository", "Repositorio"),
	SCHEMA("Schema", "Esquema"),
	TABLE("Table", "Tabla"),
	COLUMN("Column", "Columna"),
	DOCUMENT("Document", "Documento"),
	FILE("File", "Archivo"),
	SHEET("Sheet", "Planilla"),
	FOLDER("Folder", "Carpeta");

	private String type;
	private String translation;
}
