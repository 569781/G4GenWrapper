package com.gestion400.dao;

import javax.persistence.*;

import com.gestion400.bd.*;
import com.gestion400.wizard.*;

public enum RelacionRolPaginaDao {

	INSTANCE;
	
	public RelacionRolPagina find(Rol rol, Pagina pagina) {
		
		try(DBManager manager = PersistenceManager.getManager()) {
			
			TypedQuery<RelacionRolPagina> query = manager.createNamedQuery(
					"RelacionRolPagina.findRelationship", RelacionRolPagina.class);
			
			query.setParameter("pagina", pagina);
			
			query.setParameter("rol", rol);
			
			return query.getSingleResult();
			
		} catch (NoResultException e) {
			
			return null;
		}
	}
}
