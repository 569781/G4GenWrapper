package com.gestion400.wizard;

import java.io.*;

import javax.persistence.*;

import org.openxava.annotations.*;

import com.gestion400.util.*;

@View(members = "subpagina, orden")

@Entity
@Table(schema = ConstantesAplicacion.ESQUEMA_APLICACION, name = "RELPAGSUBPAG" )
public class RelacionPaginaSubpagina implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@ManyToOne(fetch = FetchType.LAZY)
	@NoCreate @NoModify @NoSearch @ReadOnly
	@JoinColumn(name = "PAGINA", referencedColumnName = "ID")
	private Pagina pagina;
	
	@Id
	@Required
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SUBPAGINA", referencedColumnName = "ID")
	@DescriptionsList(descriptionProperties = "nombre")
	private Pagina subpagina;
	
	@Column(name = "ORDEN", length = 3)
	private int orden;

	public Pagina getPagina() {
		return pagina;
	}

	public void setPagina(Pagina pagina) {
		this.pagina = pagina;
	}

	public Pagina getSubpagina() {
		return subpagina;
	}

	public void setSubpagina(Pagina subpagina) {
		this.subpagina = subpagina;
	}

	public int getOrden() {
		return orden;
	}

	public void setOrden(int orden) {
		this.orden = orden;
	}
}
