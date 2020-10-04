package com.gestion400.util;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gestion400.http.Response;

public class ServletUtil {
	
	private static ServletUtil instance;
	
	
	public enum Method{
		
		GET {
			@Override
			public void redirect(Response response) throws IOException {
			
				response.getHttpResponse().sendRedirect(response.getUrlDestino());
			}
		},
		POST {
			
			private static final String FORM = "<form id='postForm'  action='%s' method='post' style='display:none;'> %s </form>";
			private static final String INPUT_FORM = "<input type='hidden' name='%s' value='%s' />";
			private static final String JS_FORM = "<script>document.getElementById('postForm').submit();</script>";
			
			@Override
			public void redirect(Response response) throws IOException {
				
				HttpServletResponse httpResponse = response.getHttpResponse();

				httpResponse.setContentType("text/html");
				httpResponse.setCharacterEncoding("UTF-8");
				
				PrintWriter out = httpResponse.getWriter();
				
				out.print(String.format(FORM, response.getUrlDestino(), getInputs(response)) + JS_FORM);
			}
			
			private String getInputs(Response response) {
				
				StringBuilder parametros = new StringBuilder();
				
				
				for (Entry<String, Object> parametro : response.getParametros().entrySet()) {
					
					parametros.append(String.format(INPUT_FORM, parametro.getKey(), parametro.getValue()));
				}
				
				
				return parametros.toString();
			}
		};
		
		public abstract void redirect(Response response) throws IOException;
	}
	
	
	public static ServletUtil getInstance() {
		
		if(instance == null) instance = new ServletUtil();
		
		return instance;
	}
	
	
	public void redirect(Response response) throws IOException {
		redirect(response, Method.GET);
	}
	
	
	public void redirect(Response response, Method tipoRedireccion) throws IOException {
		tipoRedireccion.redirect(response);
	}
	
	
	public void fordward(String url, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher(url).forward(request, response);
	}

}
