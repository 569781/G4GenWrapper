package com.gestion400.dao;

import java.util.*;

import javax.persistence.*;

import org.openxava.jpa.*;

import com.gestion400.g4genwrapper.modelo.*;


public enum AnuncioDao {
	
	INSTANCE;
	
	
	private static final String SQL_ANUNCIOS = 
			"FROM Anuncio ORDER BY fechaDePublicacion DESC";
	
	public List<Anuncio> findAnuncios() {
		
		TypedQuery<Anuncio> q = XPersistence.getManager().createQuery(SQL_ANUNCIOS, Anuncio.class);

		return q.getResultList();
	}
	
	
	private static final String SQL_NUM_ANUNCIOS = "SELECT COUNT(*) FROM Anuncio";
	
	public long countAnuncios() {
		
		TypedQuery<Long> q = 
				XPersistence.getManager().createQuery(SQL_NUM_ANUNCIOS, Long.class);
		
		return q.getSingleResult();
	}
	
}
