package com.gestion400.wizard;

import java.util.*;

import javax.persistence.*;

import org.openxava.annotations.*;
import org.openxava.model.*;

import com.gestion400.util.*;

@View(members = "General{menuPrincipal;nombre}, Paginas{paginas}")

@Entity
@Table(schema = ConstantesAplicacion.ESQUEMA_APLICACION, name = "MENU")
public class Menu extends Identifiable{

	@Column(name = "NOMBRE", length = 50)
	private String nombre;

	@Column(name = "PRINCIPAL")
	private boolean menuPrincipal;
	
	@OneToMany(mappedBy = "menu", cascade = CascadeType.REMOVE)
	@ListProperties(value = "pagina.nombre, pagina.url, orden")
	@OrderBy("orden asc")
	private List<RelacionMenuPagina> paginas;

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public boolean isMenuPrincipal() {
		return menuPrincipal;
	}

	public void setMenuPrincipal(boolean menuPrincipal) {
		this.menuPrincipal = menuPrincipal;
	}

	public List<RelacionMenuPagina> getPaginas() {
		return paginas;
	}

	public void setPaginas(List<RelacionMenuPagina> paginas) {
		this.paginas = paginas;
	}
}