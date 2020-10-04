package com.gestion400.g4genwrapper.modelo;

import javax.persistence.*;

import org.openxava.annotations.*;

import com.gestion400.util.*;

@View(members = "codigo, descripcion")

@Table(schema = ConstantesAplicacion.ESQUEMA_APLICACION, name ="PROVINCIA")
@Entity
public class Provincia {

	@Id
	@Column(name = "CODIGO", length = 2)
	private int codigo;
	
	@Required
	@Column(name = "DESCRIPCION", length = 25)
	private String descripcion;

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
}
