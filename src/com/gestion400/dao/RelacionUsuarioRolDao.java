package com.gestion400.dao;

import javax.persistence.*;

import com.gestion400.bd.*;
import com.gestion400.wizard.*;

public enum RelacionUsuarioRolDao {

	INSTANCE;
	
	public RelacionUsuarioRol find(Usuario usuario, Rol rol) {
		
		try(DBManager manager = PersistenceManager.getManager()) {
			
			TypedQuery<RelacionUsuarioRol> query = manager.createNamedQuery(
					"RelacionUsuarioRol.findRelationship", RelacionUsuarioRol.class);
			
			query.setParameter("usuario", usuario);
			
			query.setParameter("rol", rol);
			
			return query.getSingleResult();
			
		} catch (NoResultException e) {
			
			return null;
		}
	}
}
