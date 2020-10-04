package com.gestion400.dao;

import javax.persistence.*;

import com.gestion400.bd.*;
import com.gestion400.g4genwrapper.modelo.*;

public enum MunicipioDao {
	
	INSTANCE;
	
	private static final String SQL_FIND_MUNICIPIO = 
			"FROM Municipio p WHERE p.provincia.codigo = :codigoProvincia and p.codigo = :codigoMunicipio";
	
	public Municipio find(int codigoProvincia, int codigoMunicipio) {
		
		try(DBManager manager = PersistenceManager.getManager()) {
			
			TypedQuery<Municipio> query = manager.createQuery(SQL_FIND_MUNICIPIO, Municipio.class);
			
			query.setParameter("codigoProvincia", codigoProvincia);
			
			query.setParameter("codigoMunicipio", codigoMunicipio);
			
			return query.getSingleResult();
			
		} catch (NoResultException e) {
			
			return null;
		}
	}

}
