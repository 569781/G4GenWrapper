package com.gestion400.g4genwrapper.modelo;

import javax.persistence.*;

import com.gestion400.util.*;

@Table(schema = ConstantesAplicacion.ESQUEMA_APLICACION, name ="PAIS")
@Entity
public class Pais {
	
	@Id
	@Column(name = "CODIGO", length = 3)
	private int codigo;
	
	@Column(name = "DESCRIPCION", length = 60)
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
