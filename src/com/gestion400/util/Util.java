package com.gestion400.util;

import org.openxava.util.*;

public class Util {
	
	public static String cut(String cadena, int maxDimension) {
		
		String resultado = cadena;
		
		if(!Is.empty(cadena) && cadena.length() > maxDimension) {
			
			resultado = resultado.substring(0,maxDimension);
		}
		
		return resultado;
	}
}
