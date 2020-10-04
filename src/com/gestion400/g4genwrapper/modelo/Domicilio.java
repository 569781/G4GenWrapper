package com.gestion400.g4genwrapper.modelo;

import java.math.*;

import javax.persistence.*;

import org.openxava.annotations.*;

import com.gswrapper.modelo.anotaciones.*;

@View(members = "via,denominacion;" + 
				"numero,bis,numero2,bis2,kilometro;" + 
				"bloque,portal,escalera,planta,puerta;" + 
				"provincia, municipio, codigoPostal;" + 
				"refCatastralManzana, refCatastralCentro, refCatastralCargo, digitoControl1, digitoControl2")

@Embeddable
public class Domicilio {
	
	@GSACampo("via")
	@Required
	@Column(name="VIA", length = 5)
	private String via;
	
	@GSACampo("denominacion")
	@Required
	@Column(name="DENOMINACION", length = 25)
	private String denominacion;
	
	@GSACampo("numero")
	@Column(name="NUMERO", length = 4)
	private Integer numero;
	
	@GSACampo("bis")
	@Column(name = "BIS", length = 1)
	private String bis;
	
	@GSACampo("numero2")
	@Column(name="NUMERO2", length = 4)
	private Integer numero2;
	
	@GSACampo("bis2")
	@Column(name = "BIS2", length = 1)
	private String bis2;
	
	@GSACampo("kilometro")
	@Column(name = "KILOMETRO", length = 4 , scale = 3)
	private BigDecimal kilometro;
	
	@GSACampo("bloque")
	@Column(name = "BLOQUE", length = 4)
	private String bloque;
	
	@GSACampo("portal")
	@Column(name = "PORTAL", length = 4)
	private String portal;
	
	@GSACampo("escalera")
	@Column(name = "ESCALERA", length = 2)
	private String escalera;
	
	@GSACampo("planta")
	@Column(name = "PLANTA", length = 3)
	private String planta;
	
	@GSACampo("puerta")
	@Column(name = "PUERTA", length = 4)
	private String puerta;
	
	@NoCreate @NoModify @Required
	@DescriptionsList
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PROVINCIA", referencedColumnName = "CODIGO", insertable = false, updatable = false)
	private Provincia provincia;
	
	@GSACampo("provincia")
	@Transient
	private Integer codigoProvincia;

	@NoCreate @NoModify @Required
	@DescriptionsList(depends = "domicilio.provincia.codigo", condition = "${provincia.codigo} = ?")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "MUNICIPIO", referencedColumnName = "CODIGO"),
			@JoinColumn(name = "PROVINCIA", referencedColumnName = "PROVINCIA") })
	private Municipio municipio;
	
	@GSACampo("municipio")
	@Transient
	private Integer codigoMunicipio;
	
	@GSACampo("codigoPostal")
	@Column(name = "CODIGOPOSTAL", length = 5)
	private Integer codigoPostal;
	
	@GSACampo("catastralManzana")
	@Column(name = "CATASTRALMANZANA", length = 7)
	private String refCatastralManzana;
	
	@GSACampo("catastralCentro")
	@Column(name = "CATASTRALCENTRO", length = 7)
	private String refCatastralCentro;
	
	@GSACampo("catastralCargo")
	@Column(name = "CATASTRALCARGO", length = 4)
	private Integer refCatastralCargo;	
	
	@GSACampo("catastralControl1")
	@Column(name = "CATASTRALCONTROL1", length = 1)
	private String digitoControl1;
	
	@GSACampo("catastralControl2")
	@Column(name = "CATASTRALCONTROL2", length = 1)
	private String digitoControl2;

	public String getVia() {
		return via;
	}

	public void setVia(String via) {
		this.via = via;
	}

	public String getDenominacion() {
		return denominacion;
	}

	public void setDenominacion(String denominacion) {
		this.denominacion = denominacion;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public String getBis() {
		return bis;
	}

	public void setBis(String bis) {
		this.bis = bis;
	}

	public Integer getNumero2() {
		return numero2;
	}

	public void setNumero2(Integer numero2) {
		this.numero2 = numero2;
	}

	public String getBis2() {
		return bis2;
	}

	public void setBis2(String bis2) {
		this.bis2 = bis2;
	}

	public BigDecimal getKilometro() {
		return kilometro;
	}

	public void setKilometro(BigDecimal kilometro) {
		this.kilometro = kilometro;
	}

	public String getBloque() {
		return bloque;
	}

	public void setBloque(String bloque) {
		this.bloque = bloque;
	}

	public String getPortal() {
		return portal;
	}

	public void setPortal(String portal) {
		this.portal = portal;
	}

	public String getEscalera() {
		return escalera;
	}

	public void setEscalera(String escalera) {
		this.escalera = escalera;
	}

	public String getPlanta() {
		return planta;
	}

	public void setPlanta(String planta) {
		this.planta = planta;
	}

	public String getPuerta() {
		return puerta;
	}

	public void setPuerta(String puerta) {
		this.puerta = puerta;
	}

	public Provincia getProvincia() {
		return provincia;
	}

	public void setProvincia(Provincia provincia) {
		this.provincia = provincia;
	}

	public Integer getCodigoProvincia() {
		return codigoProvincia;
	}

	public void setCodigoProvincia(Integer codigoProvincia) {
		this.codigoProvincia = codigoProvincia;
	}

	public Municipio getMunicipio() {
		return municipio;
	}

	public void setMunicipio(Municipio municipio) {
		this.municipio = municipio;
	}

	public Integer getCodigoMunicipio() {
		return codigoMunicipio;
	}

	public void setCodigoMunicipio(Integer codigoMunicipio) {
		this.codigoMunicipio = codigoMunicipio;
	}

	public Integer getCodigoPostal() {
		return codigoPostal;
	}

	public void setCodigoPostal(Integer codigoPostal) {
		this.codigoPostal = codigoPostal;
	}

	public String getRefCatastralManzana() {
		return refCatastralManzana;
	}

	public void setRefCatastralManzana(String refCatastralManzana) {
		this.refCatastralManzana = refCatastralManzana;
	}

	public String getRefCatastralCentro() {
		return refCatastralCentro;
	}

	public void setRefCatastralCentro(String refCatastralCentro) {
		this.refCatastralCentro = refCatastralCentro;
	}

	public Integer getRefCatastralCargo() {
		return refCatastralCargo;
	}

	public void setRefCatastralCargo(Integer refCatastralCargo) {
		this.refCatastralCargo = refCatastralCargo;
	}

	public String getDigitoControl1() {
		return digitoControl1;
	}

	public void setDigitoControl1(String digitoControl1) {
		this.digitoControl1 = digitoControl1;
	}

	public String getDigitoControl2() {
		return digitoControl2;
	}

	public void setDigitoControl2(String digitoControl2) {
		this.digitoControl2 = digitoControl2;
	}
}
