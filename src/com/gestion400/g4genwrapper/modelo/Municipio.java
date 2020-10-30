package com.gestion400.g4genwrapper.modelo;

import java.io.*;

import javax.persistence.*;

import org.openxava.annotations.*;

import com.gestion400.util.*;
import com.gswrapper.modelo.anotaciones.*;

@View(members = "codigo; provincia, descripcion; codigoPostal")

@Tab(properties = "provincia.descripcion, descripcion")

@Table(schema = ConstantesAplicacion.ESQUEMA_APLICACION, name ="MUNICIPIO")
@Entity
public class Municipio implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@PrePersist @PreUpdate
	private synchronized void onPrePersistUpdate() {
		
		actualizado = true;
	}
	
	@Id
	@Column(name = "CODIGO", length = 3)
	private int codigo;
	
	@Id
	@NoCreate @NoModify
	@DescriptionsList(descriptionProperties = "descripcion")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PROVINCIA", referencedColumnName = "CODIGO")
	private Provincia provincia;
	
	@GSACampo("descripcion")
	@Required
	@Column(name = "DESCRIPCION", length = 50)
	private String descripcion;
	
	@GSACampo("codigoPostal")
	@Required
	@Column(name = "CP", length = 5)
	private Integer codigoPostal;
	
	@Column(name = "ACTUALIZADO")
	private boolean actualizado;
	
	@Transient
	private boolean eliminado;

	public Provincia getProvincia() {
		return provincia;
	}

	public void setProvincia(Provincia provincia) {
		this.provincia = provincia;
	}

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

	public Integer getCodigoPostal() {
		return codigoPostal;
	}

	public void setCodigoPostal(Integer codigoPostal) {
		this.codigoPostal = codigoPostal;
	}

	public boolean isActualizado() {
		return actualizado;
	}

	public void setActualizado(boolean actualizado) {
		this.actualizado = actualizado;
	}

	public boolean isEliminado() {
		return eliminado;
	}

	public void setEliminado(boolean eliminado) {
		this.eliminado = eliminado;
	}
}
