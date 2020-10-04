package com.gestion400.wizard;

import java.io.*;

import javax.persistence.*;

import org.openxava.annotations.*;
import com.gestion400.util.*;

@View(members = "pagina, orden")

@Entity
@Table(schema = ConstantesAplicacion.ESQUEMA_APLICACION, name = "RELMENPAG" )
public class RelacionMenuPagina implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@ManyToOne(fetch = FetchType.LAZY)
	@NoCreate @NoModify @NoSearch @ReadOnly
	@JoinColumn(name = "MENU", referencedColumnName = "ID")
	private Menu menu;
	
	@Id
	@Required
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PAGINA", referencedColumnName = "ID")
	@DescriptionsList(descriptionProperties = "nombre")
	private Pagina pagina;
	
	@Column(name = "ORDEN", length = 3)
	private int orden;

	public Menu getMenu() {
		return menu;
	}

	public void setMenu(Menu menu) {
		this.menu = menu;
	}

	public Pagina getPagina() {
		return pagina;
	}

	public void setPagina(Pagina pagina) {
		this.pagina = pagina;
	}

	public int getOrden() {
		return orden;
	}

	public void setOrden(int orden) {
		this.orden = orden;
	}
}
