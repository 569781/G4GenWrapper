package com.gestion400.g4genwrapper.modelo;

import javax.persistence.*;

import org.openxava.model.*;

import com.gestion400.util.*;



@Table(schema = ConstantesAplicacion.ESQUEMA_APLICACION, name="PARAMETRO")
@Entity
public class Parametro extends Identifiable{
	
	private String clave;

	private String descripcion;

	private String valor;

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}
}
