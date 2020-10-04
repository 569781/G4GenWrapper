package com.gestion400.seguridad.filtro;

import java.io.*;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;

import com.gestion400.http.*;
import com.gestion400.seguridad.servlet.*;
import com.gestion400.util.*;
import com.gestion400.wizard.*;

@WebFilter(filterName = "FiltroSeguridad", urlPatterns = "/modules/*")
public class FiltroSeguridad implements Filter{
	
	private static final String URL_RESTABLECER_PASSWORD = "/" + ConstantesAplicacion.NOMBRE_APLICACION + 
			RestablecerPasswordServlet.URL_SERVLET;
	
	private static final String KEY_RESTABLECER_PASSWORD = "urlRestablecerPassword";
	
	@Override
	public void init(FilterConfig arg0) throws ServletException {}
	
	@Override
	public void destroy() {}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
	
		HttpServletRequest request = (HttpServletRequest) req;
		
		HttpServletResponse response = (HttpServletResponse) res;
		
		filtroSeguridad(request, response, chain);
		
	}
	
	private void filtroSeguridad(HttpServletRequest request, HttpServletResponse response, FilterChain chain) 
			throws IOException, ServletException {
		
		String url = getURL(request);
		
		Usuario usuario = UsuarioUtil.getInstance().recuperarUsuarioSesion(request);
		
		if(usuario != null) {
			
			validarAccesoUsuario(request, response, chain, usuario, url);
			
		} else {
			
			response.sendRedirect("/" + ConstantesAplicacion.NOMBRE_APLICACION);
		}
		
	}
	
	private String getURL(HttpServletRequest request) {
		
		String url = ((HttpServletRequest) request).getRequestURL().toString();

		url = url.replace("https://", "");

		url = url.replace("http://", "");

		int inicioPath = url.indexOf("/");

		if (inicioPath > 0) url = url.substring(inicioPath);
		
		return url;
	}
	
	
	private void validarAccesoUsuario(HttpServletRequest request, HttpServletResponse response, FilterChain chain, 
			Usuario usuario, String url) throws ServletException, IOException {
		
		if(usuario.isSuperUsuario() || puedeVerRecurso(usuario, url)) {
			
			if(usuario.isCambiarPassword()) {
				
				request.setAttribute(InicioServlet.KEY_CABECERA_BEAN, new CabeceraBean());
				
				request.setAttribute(KEY_RESTABLECER_PASSWORD, URL_RESTABLECER_PASSWORD);
				
				ServletUtil.getInstance().fordward(ConstantesAplicacion.PAGINA_CAMBIAR_PASSWORD, request, response);
				
			} else {
				
				chain.doFilter(request, response);
			}
			
		} else {
			
			ServletUtil.getInstance().fordward(ConstantesAplicacion.PAGINA_ACCESO_RESTRINGIDO, request, response);
		}
	}
	
	private boolean puedeVerRecurso(Usuario usuario, String url) {
		
		return true;
	}
	
}
