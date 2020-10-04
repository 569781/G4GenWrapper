package com.gestion400.wizard;

import java.io.*;

import javax.persistence.*;

import com.gestion400.dao.*;
import com.gestion400.util.*;

@Entity
@Table(schema = ConstantesAplicacion.ESQUEMA_APLICACION, name = "RELUSUARIOROL")
@NamedQuery(name="RelacionUsuarioRol.findRelationship", query = "SELECT r FROM RelacionUsuarioRol r WHERE r.rol = :rol AND r.usuario = :usuario") 
public class RelacionUsuarioRol implements IRelationship, Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ROL", referencedColumnName = "ID")
	private Rol rol;

	@Id
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "USUARIO", referencedColumnName = "NIF")
	private Usuario usuario;

	public Rol getRol() {
		return rol;
	}

	public void setRol(Rol rol) {
		this.rol = rol;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
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
		
		if(reference instanceof Usuario) {
			
			setUsuario((Usuario) reference);
			
		}else{
			 
			setRol((Rol) reference);
		}
	}

	@Override
	public IRelationship find() {
		return RelacionUsuarioRolDao.INSTANCE.find(usuario, rol);
	}
}