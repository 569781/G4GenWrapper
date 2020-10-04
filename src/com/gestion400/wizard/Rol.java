package com.gestion400.wizard;

import java.util.*;

import javax.persistence.*;

import org.openxava.annotations.*;
import org.openxava.model.*;

import com.gestion400.util.*;

@View(members = "General{nombre; descripcion}, Paginas{paginas}")

@Entity
@Table(schema = ConstantesAplicacion.ESQUEMA_APLICACION, name = "ROL")
public class Rol extends Identifiable{
	
	@Required
	@Column(name = "NOMBRE", length = 50)
	private String nombre;
	
	@Column(name = "DESCRIPCION", length = 250)
	private String descripcion;
	
    @Transient
    @OneToMany 
    @ManyToManyRelationship(value= RelacionRolPagina.class,
   	 exclusionCondition="NOT EXISTS(SELECT r.rol FROM RelacionRolPagina r WHERE r.pagina = e AND r.rol.id = ?)")
    @NewAction("") @RemoveAction("")
    @AddAction("ManyToMany.add")
    @RemoveSelectedAction("ManyToMany.removeSelected")
    @NoModify
    @Condition("EXISTS(SELECT r.rol FROM RelacionRolPagina r WHERE r.rol.id = ${this.id} AND r.pagina.id = ${id})")
    @ListProperties("nombre")
    private List<Pagina> paginas;

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

	public List<Pagina> getPaginas() {
		return paginas;
	}

	public void setPaginas(List<Pagina> paginas) {
		this.paginas = paginas;
	}

}
