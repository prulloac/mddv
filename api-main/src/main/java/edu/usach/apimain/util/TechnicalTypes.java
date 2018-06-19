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
	FOLDER("Folder", "Carpeta"),
	RELATION("Relation", "Relación"),
	RELATION_MANY_ONE("Relation Many to One", "Relación Muchos a Uno"),
	RELATION_MANY_MANY("Relation Many to Many", "Relación Muchos a Muchos"),
	RELATION_DEPENDENCY("Dependency Relation", "Relación de Dependencia"),
	RELATION_USE("Use Relation", "Relación de Uso"),
	RELATION_GENERALIZATION("Generalization Relation", "Relación de Generalización");

	private String type;
	private String translation;
}
