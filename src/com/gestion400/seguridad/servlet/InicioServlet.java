package com.gestion400.seguridad.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gestion400.http.*;
import com.gestion400.util.*;
import com.gestion400.wizard.*;


@WebServlet(name = "inicioServlet", urlPatterns = InicioServlet.URL_SERVLET)
public class InicioServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	public static final String URL_SERVLET = "";
	
	public static final String KEY_LOGIN_BEAN = "loginBean";
	public static final String KEY_CABECERA_BEAN = "cabeceraBean";
	
	public static final String PAGINA_INICIO = "Inicio";
	
	private HttpServletRequest request;
	private HttpServletResponse response;
	private Usuario usuario;
	private String errores;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		setUp(req, resp);
		
		if(usuario != null) {
			
			redirigirPaginaInicio();
			
		}else {
	
			redirigirPaginaLogin();
		}
	}
	
	private void setUp(HttpServletRequest req, HttpServletResponse resp) {
		
		request = req;
		response = resp;
		usuario = UsuarioUtil.getInstance().recuperarUsuarioSesion(req);
		errores = req.getParameter(LoginServlet.KEY_ERROR);
	}
	
	private void redirigirPaginaInicio() throws IOException {
		
		response.sendRedirect(ConstantesAplicacion.RUTA_PAGINA_INICIO);
	}
	
	private void redirigirPaginaLogin() throws ServletException, IOException {
		
		request.setAttribute(KEY_CABECERA_BEAN, new CabeceraBean());
		
		request.setAttribute(KEY_LOGIN_BEAN, new LoginBean(errores));
		
		ServletUtil.getInstance().fordward(ConstantesAplicacion.PAGINA_LOGIN, request, response);
	}	
}

