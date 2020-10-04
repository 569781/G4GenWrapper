package com.gestion400.http;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

public class Response {

	private String urlDestino;
	private HttpServletResponse	httpResponse;
	private Map<String, Object> parametros;
	
	
	public Response(String urlDestino, HttpServletResponse	httpResponse) {
		
		this.urlDestino = urlDestino;
		this.httpResponse = httpResponse;
		
		parametros = new HashMap<>();
	}
	
	
	public void addParametro(String clave, Object valor) {
		
		parametros.put(clave, valor);
	}


	public String getUrlDestino() {
		return urlDestino;
	}


	public void setUrlDestino(String urlDestino) {
		this.urlDestino = urlDestino;
	}


	public HttpServletResponse getHttpResponse() {
		return httpResponse;
	}


	public void setHttpResponse(HttpServletResponse httpResponse) {
		this.httpResponse = httpResponse;
	}


	public Map<String, Object> getParametros() {
		return parametros;
	}
	
}
