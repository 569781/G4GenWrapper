package com.gestion400.wizard;

import java.util.*;

import javax.persistence.*;

import org.openxava.annotations.*;
import org.openxava.model.*;

import com.gestion400.util.*;

@View(members = "General{nombre, descripcion;url}, Subpaginas{subpaginas}")

@Entity
@Table(schema = ConstantesAplicacion.ESQUEMA_APLICACION, name = "PAGINA")
public class Pagina extends Identifiable {

	@Required
	@Column(name = "NOMBRE", length = 50)
	private String nombre;
	
	@Column(name = "DESCRIPCION", length = 250)
	private String descripcion;

	@Required
	@Column(name = "URL", length = 200)
	private String url;
	
	@OneToMany(mappedBy = "pagina", cascade = CascadeType.REMOVE)
	@ListProperties(value = "subpagina.nombre, subpagina.url, orden")
	@OrderBy("orden asc")
	private List<RelacionPaginaSubpagina> subpaginas;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public List<RelacionPaginaSubpagina> getSubpaginas() {
		return subpaginas;
	}

	public void setSubpaginas(List<RelacionPaginaSubpagina> subpaginas) {
		this.subpaginas = subpaginas;
	}
}