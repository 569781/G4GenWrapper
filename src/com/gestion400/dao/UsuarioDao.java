package com.gestion400.dao;

import javax.persistence.*;

import com.gestion400.bd.*;
import com.gestion400.wizard.*;

public enum UsuarioDao {

	INSTANCE;
	
	private static final String QUERY_VALIDAR_PASSWORD = "Usuario.validarPassword";
	
	public Usuario findUsuario(String id) {
		
		try(DBManager manager = PersistenceManager.getManager()) {
			
			return manager.find(Usuario.class, id);
			
		} catch (NoResultException e) {
			
			return null;
		}
	}
	
	public Usuario findUsuario(String nif, String password) {
		
		try(DBManager manager = PersistenceManager.getManager()) {
			
			TypedQuery<Usuario> query = manager.createNamedQuery(QUERY_VALIDAR_PASSWORD, Usuario.class);
			
			query.setParameter("nif", nif);
			
			query.setParameter("password", password);
			
			return query.getSingleResult();
			
		} catch (NoResultException e) {
			
			return null;
		}
	}
	
	private static final String SQL_UPDATE_PASSWORD = 
			"UPDATE Usuario set password = :passwordEncriptada, cambiarPassword = :cambiarPassword WHERE nif = :nif";
	
	public void cambiarPassword(String nif, String passwordEncriptada, boolean restablecer) {
		
		try (DBManager manager = PersistenceManager.getManager()) {
			
			Query query = manager.createQuery(SQL_UPDATE_PASSWORD);
			
			query.setParameter("passwordEncriptada", passwordEncriptada);
			query.setParameter("cambiarPassword", restablecer);
			query.setParameter("nif", nif);
			
			query.executeUpdate();
		}
	}
	
}
