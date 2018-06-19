package edu.usach.apimain.util;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum BusinessTypes {
	INTERNAL_POLICY("Internal Policy", "Política Interna"),
	INTERNAL_USE("Internal Use", "Uso Interno"),
	NATIONAL_LAW("National Law", "Ley Nacional"),
	INTERNATIONAL_LAW("International Law", "Ley Internacional"),
	BUSINESS_RULE("Business Rule", "Regla de Negocio"),
	LOGIC_BOND("Logic Bond", "Vínculo Lógico"),
	USE_BOND("Use Bond", "Vículo de Uso"),
	SYNONYM_BOND("Synonym Bond", "Vículo de Sinónimos");

	private String type;
	private String translation;

}
