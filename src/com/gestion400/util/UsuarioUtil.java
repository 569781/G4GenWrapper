package com.gestion400.util;

import javax.servlet.http.*;

import com.gestion400.wizard.*;

public class UsuarioUtil {
	
	private static final String KEY_USUARIO_SESION = "usuario_sesion"; 
	
	private static UsuarioUtil instance;

	public static synchronized UsuarioUtil getInstance() {
		
		if(instance == null) instance = new UsuarioUtil();
		
		return instance;
	}

	
	public void nuevoUsuarioSesion(HttpServletRequest request, Usuario usuario) {
		
		request.getSession().setAttribute(KEY_USUARIO_SESION, usuario);
	}
	
	
	public Usuario recuperarUsuarioSesion(HttpServletRequest request) {
		
		return (Usuario) request.getSession().getAttribute(KEY_USUARIO_SESION);
	}
	
}
