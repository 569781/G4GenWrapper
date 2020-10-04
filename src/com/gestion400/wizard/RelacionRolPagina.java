package com.gestion400.wizard;

import java.io.*;

import javax.persistence.*;

import com.gestion400.dao.*;
import com.gestion400.util.*;

@Entity
@Table(schema = ConstantesAplicacion.ESQUEMA_APLICACION, name = "RELROLPAGINA")
@NamedQuery(name="RelacionRolPagina.findRelationship", query = "SELECT r FROM RelacionRolPagina r WHERE r.rol = :rol AND r.pagina = :pagina") 
public class RelacionRolPagina implements IRelationship, Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@ManyToOne (fetch = FetchType.LAZY)
	@JoinColumn(name = "ROL", referencedColumnName = "ID")
	private Rol rol;

	@Id
	@ManyToOne (fetch = FetchType.LAZY)
	@JoinColumn(name = "PAGINA", referencedColumnName = "ID")
	private Pagina pagina;
	

	public Rol getRol() {
		return rol;
	}

	public void setRol(Rol rol) {
		this.rol = rol;
	}

	public Pagina getPagina() {
		return pagina;
	}

	public void setPagina(Pagina pagina) {
		this.pagina = pagina;
	}

	@Override
	public void setPrimary(Object primary) {
		setReference(primary);
	}

	@Override
	public void setSecundary(Object secundary) {
		setReference(secundary);
	}
	
	private void setReference(Object reference) {
		
		if(reference instanceof Pagina) {
			
			setPagina((Pagina) reference);
			
		}else{
			 
			setRol((Rol) reference);
		}
	}

	@Override
	public IRelationship find() {
		return RelacionRolPaginaDao.INSTANCE.find(rol, pagina);
	}
}
