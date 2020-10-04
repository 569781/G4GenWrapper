package com.gestion400.g4genwrapper.modelo;

import java.util.*;

import javax.persistence.*;

import org.openxava.annotations.*;

import com.gestion400.util.*;
import com.gswrapper.modelo.anotaciones.*;

@View(members = "General{fechaAlta, fechaBaja, fechaModificacion;" +
				"naturaleza, nif; " +
		        "pais; " +
		        "razonSocial; " +
		        "nombre, primerApellido, segundoApellido;" +
		        "telefono,email} Domicilio{domicilio}")

@Tab(properties = "nif,razonSocial")

@Table(schema = ConstantesAplicacion.ESQUEMA_APLICACION, name ="TERCERO")
@Entity
public class Tercero {
	
	@PrePersist 
	private synchronized void onPrePersist() {
		
		actualizado = true;
		
		fechaAlta = new Date();
	}
	
	@PreUpdate
	private synchronized void onPreUpdate() {
		
		actualizado = true;
		
		fechaModificacion = new Date();
	}
	
	public enum Naturaleza {	
		NIF,CIF,NIE
	}
	
	@Id
	@Column(name = "NIF", length = 9)
	private String nif;
	
	@Enumerated(EnumType.ORDINAL)
	@Column(name = "NATURALEZA")
	private Naturaleza naturaleza;
	
	@GSACampo("naturaleza")
	@Transient
	private Integer codigoNaturaleza;
	
	@Required
	@NoCreate @NoModify
	@DescriptionsList(descriptionProperties = "descripcion")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PAIS", referencedColumnName = "CODIGO")
	private Pais pais;
	
	@GSACampo("pais")
	@Transient
	private Integer codigoPais;
	
	@Required
	@Column(name = "RAZONSOCIAL", length = 60)
	private String razonSocial;
	
	@Required
	@GSACampo("nombre")
	@Column(name = "NOMBRE", length = 20)
	private String nombre;
	
	@GSACampo("primerApellido")
	@Column(name = "APELLIDO1", length = 25)
	private String primerApellido;
	
	@GSACampo("segundoApellido")
	@Column(name = "APELLIDO2", length = 25)
	private String segundoApellido;
	
	@GSACampo("telefono")
	@Column(name = "TELEFONO", length = 15)
	private String telefono; 

	@GSACampo("email")
	@Column(name = "EMAIL", length = 60)
	private String email;
	
	@ReadOnly
	@Column(name = "FECHAALTA")
	private Date fechaAlta;
	
	@GSACampo("alta")
	@Transient
	private String fechaAltaString;
	
	@ReadOnly
	@Column(name = "FECHABAJA")
	private Date fechaBaja;
	
	@GSACampo("baja")
	@Transient
	private String fechaBajaString;
	
	@ReadOnly
	@Column(name = "FACHAMODIFICACION")
	private Date fechaModificacion;
	
	@GSACampo("modificacion")
	@Transient
	private String fechaModificacionString;
	
	@Embedded
	private Domicilio domicilio;
	
	@Column(name = "ACTUALIZADO")
	private boolean actualizado;
	
	@Transient
	private boolean eliminado;

	public String getNif() {
		return nif;
	}

	public void setNif(String nif) {
		this.nif = nif;
	}

	public Naturaleza getNaturaleza() {
		return naturaleza;
	}

	public void setNaturaleza(Naturaleza naturaleza) {
		this.naturaleza = naturaleza;
	}

	public Integer getCodigoNaturaleza() {
		return codigoNaturaleza;
	}

	public void setCodigoNaturaleza(Integer codigoNaturaleza) {
		this.codigoNaturaleza = codigoNaturaleza;
	}

	public Pais getPais() {
		return pais;
	}

	public void setPais(Pais pais) {
		this.pais = pais;
	}

	public Integer getCodigoPais() {
		return codigoPais;
	}

	public void setCodigoPais(Integer codigoPais) {
		this.codigoPais = codigoPais;
	}

	public String getRazonSocial() {
		return razonSocial;
	}

	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getPrimerApellido() {
		return primerApellido;
	}

	public void setPrimerApellido(String primerApellido) {
		this.primerApellido = primerApellido;
	}

	public String getSegundoApellido() {
		return segundoApellido;
	}

	public void setSegundoApellido(String segundoApellido) {
		this.segundoApellido = segundoApellido;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getFechaAlta() {
		return fechaAlta;
	}

	public void setFechaAlta(Date fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

	public String getFechaAltaString() {
		return fechaAltaString;
	}

	public void setFechaAltaString(String fechaAltaString) {
		this.fechaAltaString = fechaAltaString;
	}

	public Date getFechaBaja() {
		return fechaBaja;
	}

	public void setFechaBaja(Date fechaBaja) {
		this.fechaBaja = fechaBaja;
	}

	public String getFechaBajaString() {
		return fechaBajaString;
	}

	public void setFechaBajaString(String fechaBajaString) {
		this.fechaBajaString = fechaBajaString;
	}

	public Date getFechaModificacion() {
		return fechaModificacion;
	}

	public void setFechaModificacion(Date fechaModificacion) {
		this.fechaModificacion = fechaModificacion;
	}

	public String getFechaModificacionString() {
		return fechaModificacionString;
	}

	public void setFechaModificacionString(String fechaModificacionString) {
		this.fechaModificacionString = fechaModificacionString;
	}

	public boolean isActualizado() {
		return actualizado;
	}

	public void setActualizado(boolean actualizado) {
		this.actualizado = actualizado;
	}

	public Domicilio getDomicilio() {
		return domicilio;
	}

	public void setDomicilio(Domicilio domicilio) {
		this.domicilio = domicilio;
	}

	public boolean isEliminado() {
		return eliminado;
	}

	public void setEliminado(boolean eliminado) {
		this.eliminado = eliminado;
	}
}
