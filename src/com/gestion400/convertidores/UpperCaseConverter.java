package com.gestion400.convertidores;

import javax.persistence.*;

import org.openxava.util.*;

public class UpperCaseConverter implements AttributeConverter<String, String>{

	@Override
	public String convertToEntityAttribute(String cadena) {		
		
		if(Is.empty(cadena)) return "";
		
		return cadena.trim();
	}
	
	@Override
	public String convertToDatabaseColumn(String cadena) {
		
		if(Is.empty(cadena)) return "";

		return cadena.toUpperCase();
	}

}
