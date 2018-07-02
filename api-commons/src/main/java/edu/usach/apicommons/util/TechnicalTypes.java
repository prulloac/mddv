package edu.usach.apicommons.util;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TechnicalTypes {
	REPOSITORY("Repository", "Repositorio", null),
	RDBMS("Relational Database", "Base de datos relacional", REPOSITORY),
	TABLE("Table", "Tabla", RDBMS),
	COLUMN("Column", "Columna", TABLE),
	DOCUMENT_DB("Document Database", "Base de datos de documentos", REPOSITORY),
	COLLECTION("Collection", "Colección", DOCUMENT_DB),
	COLLECTION_ATTRIBUTE("Collection attribute", "Atributo de colección", COLLECTION),
	FOLDER("Folder", "Carpeta", REPOSITORY),
	DOCUMENT("Document", "Documento", FOLDER),
	FILE("File", "Archivo", FOLDER),
	SHEET("Sheet", "Planilla", FOLDER),
	RELATION("Relation", "Relación", null),
	RELATION_MANY_ONE("Relation Many to One", "Relación Muchos a Uno", TABLE),
	RELATION_DEPENDENCY("Dependency Relation", "Relación de Dependencia", COLLECTION),
	RELATION_USE("Use Relation", "Relación de Uso", COLLECTION),
	RELATION_GENERALIZATION("Generalization Relation", "Relación de Generalización", COLLECTION);

	private String type;
	private String translation;
	private TechnicalTypes parent;
}
