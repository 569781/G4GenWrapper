package com.gestion400.dao;

import javax.persistence.*;

import com.gestion400.bd.*;
import com.gestion400.g4genwrapper.modelo.*;

public enum TerceroDao {
	
	INSTANCE;
	
	public Tercero findTercero(String nif) {
		
		try(DBManager manager = PersistenceManager.getManager()) {
			
			return manager.find(Tercero.class, nif);
			
		} catch (NoResultException e) {
			
			return null;
		}
	}

}
