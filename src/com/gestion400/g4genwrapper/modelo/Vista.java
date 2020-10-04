package com.gestion400.g4genwrapper.modelo;

import org.openxava.annotations.ReadOnly;
import org.openxava.annotations.View;

@View(members = "nif; nombre,correo")

public class Vista {
	
	private String nif;
	
	@ReadOnly
	private String nombre;
	
	@ReadOnly
	private String correo;

	public String getNif() {
		return nif;
	}

	public void setNif(String nif) {
		this.nif = nif;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}
}
