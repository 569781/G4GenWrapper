package com.gestion400.http;

import java.util.ArrayList;
import java.util.List;

import org.openxava.util.*;

import com.gestion400.seguridad.servlet.*;
import com.gestion400.util.*;

public class LoginBean {
	
	private String urlValidarCertificado;
	
	private String urlValidarUsuarioPassword;
	
	private List<String> erroresLogin = new ArrayList<>();
	
	public enum AuthenticationError {
		
		SERVER("Error en el servidor"),
		LOGIN("Usuario o contraseña incorrectos");
		
		private String traduccion;
		
		AuthenticationError(String traduccion) {
			
			this.traduccion = traduccion;
		}
		
		public String traduccion() {
			return traduccion;
		}
	}
	
	public LoginBean(String listadoErrores) {
		
		urlValidarUsuarioPassword = "/" + ConstantesAplicacion.NOMBRE_APLICACION + LoginServlet.URL_SERVLET;
		
		if(!Is.empty(listadoErrores)) inicializarErrores(listadoErrores);
	}
	
	public LoginBean() {
		
		this("");
	}
	
	public static final String SEPARADOR_ERRORES = ";";
	
	private void inicializarErrores(String listadoErrores) {
		
		String traduccionError;
		
		for (String error : listadoErrores.split(SEPARADOR_ERRORES)) {
			
			try {
				
				AuthenticationError tipoError = AuthenticationError.valueOf(error);
				
				traduccionError = tipoError.traduccion;
				
			} catch (IllegalArgumentException e) {
				
				traduccionError = error;
			}
			
			erroresLogin.add(traduccionError);
		}	
	}
	
	public String getUrlValidarCertificado() {
		return urlValidarCertificado;
	}

	public String getUrlValidarUsuarioPassword() {
		return urlValidarUsuarioPassword;
	}

	public List<String> getErroresLogin() {
		return erroresLogin;
	}
}
