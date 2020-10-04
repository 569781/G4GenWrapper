package com.gestion400.http;

import com.gestion400.util.*;

public class CabeceraBean {
	
	private String titulo;
	
	private String tituloAplicacion;
	
	private String entidad;
	
	private String urlEscudo;
	
	public CabeceraBean() {
		
		setUp();
	}
	
	private void setUp() {
		
		tituloAplicacion = ConstantesAplicacion.DESCRIPCION_APLICACION;
		
		titulo = tituloAplicacion + " " + ConstantesAplicacion.ENTIDAD;
		
		entidad = ConstantesAplicacion.ENTIDAD ;
		
		urlEscudo = "/" + ConstantesAplicacion.NOMBRE_APLICACION + "/images/logo.png";
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getTituloAplicacion() {
		return tituloAplicacion;
	}

	public void setTituloAplicacion(String tituloAplicacion) {
		this.tituloAplicacion = tituloAplicacion;
	}

	public String getEntidad() {
		return entidad;
	}

	public void setEntidad(String entidad) {
		this.entidad = entidad;
	}

	public String getUrlEscudo() {
		return urlEscudo;
	}

	public void setUrlEscudo(String urlEscudo) {
		this.urlEscudo = urlEscudo;
	}
}
