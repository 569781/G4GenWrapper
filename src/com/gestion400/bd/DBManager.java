package com.gestion400.bd;

import java.io.Closeable;
import java.util.*;

import javax.persistence.*;
import javax.persistence.criteria.*;
import javax.persistence.metamodel.Metamodel;

public class DBManager implements EntityManager, Closeable {

	private EntityManager manager;
	
	private boolean rollback;
	
	public DBManager(EntityManager manager) {
		
		this.manager = manager;
		
		rollback = false;
	}
	
	@Override
	public void persist(Object entity) {
		manager.persist(entity);
	}

	@Override
	public <T> T merge(T entity) {
		return manager.merge(entity);
	}

	@Override
	public void remove(Object entity) {
		manager.remove(entity);
	}

	@Override
	public <T> T find(Class<T> entityClass, Object primaryKey) {
		return manager.find(entityClass, primaryKey);
	}

	@Override
	public <T> T find(Class<T> entityClass, Object primaryKey, Map<String, Object> properties) {
		return manager.find(entityClass, primaryKey, properties);
	}

	@Override
	public <T> T find(Class<T> entityClass, Object primaryKey, LockModeType lockMode) {
		return manager.find(entityClass, primaryKey, lockMode);
	}

	@Override
	public <T> T find(Class<T> entityClass, Object primaryKey, LockModeType lockMode, Map<String, Object> properties) {
		return manager.find(entityClass, primaryKey, lockMode, properties);
	}

	@Override
	public <T> T getReference(Class<T> entityClass, Object primaryKey) {
		return manager.getReference(entityClass, primaryKey);
	}

	@Override
	public void flush() {
		manager.flush();
	}

	@Override
	public void setFlushMode(FlushModeType flushMode) {
		manager.setFlushMode(flushMode);
	}

	@Override
	public FlushModeType getFlushMode() {
		return manager.getFlushMode();
	}

	@Override
	public void lock(Object entity, LockModeType lockMode) {
		manager.lock(entity, lockMode);
	}

	@Override
	public void lock(Object entity, LockModeType lockMode, Map<String, Object> properties) {
		manager.lock(entity, lockMode, properties);	
	}

	@Override
	public void refresh(Object entity) {
		manager.refresh(entity);
	}

	@Override
	public void refresh(Object entity, Map<String, Object> properties) {
		manager.refresh(entity, properties);
	}

	@Override
	public void refresh(Object entity, LockModeType lockMode) {
		manager.refresh(entity, lockMode);
	}

	@Override
	public void refresh(Object entity, LockModeType lockMode, Map<String, Object> properties) {
		manager.refresh(entity, lockMode, properties);
		
	}

	@Override
	public void clear() {
		manager.clear();
	}

	@Override
	public void detach(Object entity) {
		manager.detach(entity);
	}

	@Override
	public boolean contains(Object entity) {
		return manager.contains(entity);
	}

	@Override
	public LockModeType getLockMode(Object entity) {
		return manager.getLockMode(entity);
	}

	@Override
	public void setProperty(String propertyName, Object value) {
		manager.setProperty(propertyName, value);
	}

	@Override
	public Map<String, Object> getProperties() {
		return manager.getProperties();
	}

	@Override
	public Query createQuery(String qlString) {
		return manager.createQuery(qlString);
	}

	@Override
	public <T> TypedQuery<T> createQuery(CriteriaQuery<T> criteriaQuery) {
		return manager.createQuery(criteriaQuery);
	}

	@Override
	public <T> TypedQuery<T> createQuery(String qlString, Class<T> resultClass) {
		return manager.createQuery(qlString, resultClass);
	}

	@Override
	public Query createNamedQuery(String name) {
		return manager.createNamedQuery(name);
	}

	@Override
	public <T> TypedQuery<T> createNamedQuery(String name, Class<T> resultClass) {
		return manager.createNamedQuery(name, resultClass);
	}

	@Override
	public Query createNativeQuery(String sqlString) {
		return manager.createNativeQuery(sqlString);
	}

	@Override
	public Query createNativeQuery(String sqlString, Class resultClass) {
		return manager.createNativeQuery(sqlString, resultClass);
	}

	@Override
	public Query createNativeQuery(String sqlString, String resultSetMapping) {
		return manager.createNativeQuery(sqlString, resultSetMapping);
	}

	@Override
	public void joinTransaction() {
		manager.joinTransaction();
	}

	@Override
	public <T> T unwrap(Class<T> cls) {
		return manager.unwrap(cls);
	}

	@Override
	public Object getDelegate() {
		return manager.getDelegate();
	}

	@Override
	public boolean isOpen() {
		return manager.isOpen();
	}

	@Override
	public EntityTransaction getTransaction() {
		return manager.getTransaction();
	}

	@Override
	public EntityManagerFactory getEntityManagerFactory() {
		return manager.getEntityManagerFactory();
	}

	@Override
	public CriteriaBuilder getCriteriaBuilder() {
		return manager.getCriteriaBuilder();
	}

	@Override
	public Metamodel getMetamodel() {
		return manager.getMetamodel();
	}

	public void rollback() {
		
		if(manager != null && manager.isOpen()){
			
				
			manager.getTransaction().rollback();
			
			rollback = true;
		}
	}
	
	@Override
	public void close() {
		
		if(manager != null && manager.isOpen()) {
			
			try {
				
				if(manager.getTransaction().isActive() && !rollback) {
					
					manager.getTransaction().commit();
					
				} else {
					manager.flush();
				}
				
			} finally {
				manager.close();
			}
		}
	}

	@Override
	public <T> EntityGraph<T> createEntityGraph(Class<T> arg0) {
		return null;
	}

	@Override
	public EntityGraph<?> createEntityGraph(String arg0) {
		return null;
	}

	@Override
	public StoredProcedureQuery createNamedStoredProcedureQuery(String arg0) {
		return null;
	}

	@Override
	public Query createQuery(CriteriaUpdate arg0) {
		return null;
	}

	@Override
	public Query createQuery(CriteriaDelete arg0) {
		return null;
	}

	@Override
	public StoredProcedureQuery createStoredProcedureQuery(String arg0) {
		return null;
	}

	@Override
	public StoredProcedureQuery createStoredProcedureQuery(String arg0, Class... arg1) {
		return null;
	}

	@Override
	public StoredProcedureQuery createStoredProcedureQuery(String arg0, String... arg1) {
		return null;
	}

	@Override
	public EntityGraph<?> getEntityGraph(String arg0) {
		return null;
	}

	@Override
	public <T> List<EntityGraph<? super T>> getEntityGraphs(Class<T> arg0) {
		return null;
	}

	@Override
	public boolean isJoinedToTransaction() {
		return false;
	}
}
