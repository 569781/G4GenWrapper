package com.gestion400.util;

import org.apache.commons.codec.digest.DigestUtils;
import org.openxava.util.Is;

public class PasswordUtil {
	
	private static PasswordUtil instance;
	
	public static PasswordUtil getInstance() {
		
		if(instance == null) instance = new PasswordUtil();
		
		return instance;
	}
	
	
	public String encriptar(String cadena) {
		
		String cadenaEnciprada = null;
		
		if(!Is.empty(cadena)) {
			
			cadenaEnciprada = DigestUtils.sha1Hex(cadena.getBytes());
		}
		
		return cadenaEnciprada;
	}
}
