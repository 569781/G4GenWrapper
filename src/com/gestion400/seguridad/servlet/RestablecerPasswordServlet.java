package com.gestion400.seguridad.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.openxava.util.Is;

import com.gestion400.dao.*;
import com.gestion400.util.*;
import com.gestion400.wizard.*;

@WebServlet(name = "restablecerPassword", urlPatterns= RestablecerPasswordServlet.URL_SERVLET)
public class RestablecerPasswordServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	public static final String URL_SERVLET = "/servlet/restablecerPassword";
	
	private static final String KEY_PASSWORD = "pass1";
	private static final String KEY_PASSWORD_REPETIDA = "pass2";
	
	private String password;
	private String passwordRepetida;
	
	private Usuario usuario;
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		System.out.println("RestablecerPasswordServlet");
		
		setUp(req);
		
		if(sePuedeRestablecer()) restablecerPassword(req);
		
		resp.sendRedirect(ConstantesAplicacion.RUTA_PAGINA_INICIO);
	}
	
	private void setUp(HttpServletRequest req) {
		
		usuario = UsuarioUtil.getInstance().recuperarUsuarioSesion(req);
		
		password = req.getParameter(KEY_PASSWORD);
		passwordRepetida = req.getParameter(KEY_PASSWORD_REPETIDA);
	}
	
	private void restablecerPassword(HttpServletRequest req) {
		
		String idUsuario = usuario.getId();
		
		String passwordEncriptada = PasswordUtil.getInstance().encriptar(password);
		
		UsuarioDao.INSTANCE.cambiarPassword(idUsuario, passwordEncriptada, false);
		
		usuario.setCambiarPassword(false);
		
		System.out.println(UsuarioUtil.getInstance().recuperarUsuarioSesion(req).isCambiarPassword());
	}
	
	private boolean sePuedeRestablecer() {
		
		return passwordCorrectas() && UsuarioValido();
	}
	
	public static final int MIN_LENGTH_PASSWORD = 8;
	
	private boolean passwordCorrectas() {
		
		return passwordNoVacias() && dimensionPasswordCorrecta() && password.equals(passwordRepetida);
	}
	
	private boolean passwordNoVacias() {
		
		return !Is.empty(password) && !Is.empty(passwordRepetida);
	}
	
	private boolean dimensionPasswordCorrecta() {
		
		return password.length() >= MIN_LENGTH_PASSWORD;
	}
	
	private boolean UsuarioValido() {
		
		return !Is.empty(usuario) && usuario.isCambiarPassword();
	}
}
