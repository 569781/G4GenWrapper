package com.gestion400.seguridad.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.openxava.jpa.XPersistence;
import org.openxava.util.Is;
import org.openxava.validators.ValidationException;

import com.gestion400.dao.*;
import com.gestion400.http.LoginBean.*;
import com.gestion400.http.Response;
import com.gestion400.util.*;
import com.gestion400.util.ServletUtil.Method;
import com.gestion400.wizard.*;

@WebServlet(name = "login", urlPatterns = LoginServlet.URL_SERVLET)
public class LoginServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	public static final String URL_SERVLET = "/servlet/Login";
	
	public static final String KEY_ERROR = "error";
	
	private static final String KEY_USER = "usuario";
	private static final String KEY_PASSWORD = "password";
	

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try {
			
			System.out.println("LoginServlet");
			
			login(request, response);
			
		} catch (ValidationException e) {
			
			redireccionarPaginaLogin(response, AuthenticationError.LOGIN);
			
		} catch (Exception ex) {
			
			ex.printStackTrace();
			
			redireccionarPaginaLogin(response, AuthenticationError.SERVER);
			
		}finally {
			
			XPersistence.commit();
		}
	}
	
	private void login(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		Usuario usuario = getUsuario(request);
		
		UsuarioUtil.getInstance().nuevoUsuarioSesion(request, usuario);
		
		if(usuario != null) {
			
			redirigirAplicacion(response);
			
		} else {
			
			redireccionarPaginaLogin(response, AuthenticationError.LOGIN);
		}		
	}
	
	private Usuario getUsuario(HttpServletRequest request) {
		
		String nif =  request.getParameter(KEY_USER);
		String password =  request.getParameter(KEY_PASSWORD);
		
		if(Is.empty(nif) || Is.empty(password)) throw new ValidationException();
		
		String passwordEncriptada = PasswordUtil.getInstance().encriptar(password);
		
		return UsuarioDao.INSTANCE.findUsuario(nif, passwordEncriptada);
	}
	
	private void redirigirAplicacion(HttpServletResponse response) throws IOException {
		
		Response respuesta = new Response(ConstantesAplicacion.RUTA_PAGINA_INICIO, response);
		
		ServletUtil.getInstance().redirect(respuesta, Method.GET);
	}
	
	private void redireccionarPaginaLogin(HttpServletResponse response, AuthenticationError error) throws IOException {
		
		Response respuesta = new Response("/" + ConstantesAplicacion.NOMBRE_APLICACION + "/", response);
		
		respuesta.addParametro(KEY_ERROR, error);
		
		ServletUtil.getInstance().redirect(respuesta, Method.POST);
	}
}
