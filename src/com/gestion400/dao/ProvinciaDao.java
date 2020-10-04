package com.gestion400.dao;

import javax.persistence.*;

import com.gestion400.bd.*;
import com.gestion400.g4genwrapper.modelo.*;

public enum ProvinciaDao {

	INSTANCE;
	
	public Provincia find(int codigoProvincia) {
		
		try(DBManager manager = PersistenceManager.getManager()) {
			
			return manager.find(Provincia.class, codigoProvincia);
			
		} catch (NoResultException e) {
			
			return null;
		}
	}
	
}
