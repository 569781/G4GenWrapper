package com.gestion400.bd;

import java.sql.*;

import javax.persistence.Query;

import org.apache.log4j.*;
import org.hibernate.*;
import org.openxava.hibernate.*;
import org.openxava.jpa.*;

import liquibase.*;
import liquibase.database.jvm.*;
import liquibase.exception.*;
import liquibase.resource.*;

public class GestorActualizacionDB2i {

	
	public static void actualizarBaseDatos(String rutaRelativaChangelog,String schema) throws SQLException {
		try {

			crearTablaChangeLog(schema);
			
			Session hibernateSession = XPersistence.getManager().unwrap(Session.class);

			hibernateSession.doWork(new org.hibernate.jdbc.Work() {

				@Override
				public void execute(Connection connection) throws SQLException {
					DB2iDatabase database = new DB2iDatabase();
					try {

						database.setConnection(new JdbcConnection(connection));

						database.setLiquibaseSchemaName(schema);
						
						database.setDefaultSchemaName(schema);

						Liquibase liquibase = new liquibase.Liquibase(rutaRelativaChangelog,
								new ClassLoaderResourceAccessor(), database);
						
						liquibase.clearCheckSums();
						
						liquibase.update(new Contexts(), new LabelExpression());
						
					} catch (LiquibaseException ex) {
						
						if(connection != null) connection.close();
						
						throw new SQLException(ex);
					}
				}
			});
			
		} finally {
			
			XHibernate.commit();
			XPersistence.commit();
		}
	}
	
	private static final String SQL_EXISTS_TABLE = 
		"SELECT COUNT(*) FROM SYSIBM.SQLTABLES WHERE TABLE_SCHEM = '%s' " + 
		"AND TABLE_TYPE = 'TABLE' AND TABLE_NAME = 'DATABASECHANGELOGLOCK'";
	
	private static final String SQL_CREATE_DATABASECHANGELOGLOCK = 
		"CREATE TABLE %s.DATABASECHANGELOGLOCK (ID INTEGER NOT NULL, LOCKED SMALLINT NOT NULL, " + 
		"LOCKGRANTED TIMESTAMP, LOCKEDBY VARCHAR(255), CONSTRAINT %s.PK_DBCHGLOGLOCK PRIMARY KEY (ID))";

	private static void crearTablaChangeLog(String schema){
		try{
			Query query = XPersistence.getManager().createNativeQuery(String.format(SQL_EXISTS_TABLE, schema));
			
			boolean existe = ((Number)query.getSingleResult()).intValue() != 0 ;
			
			if(!existe){
				query = XPersistence.getManager().createNativeQuery(String.format(SQL_CREATE_DATABASECHANGELOGLOCK, schema,schema));
				
				query.executeUpdate();
			}
			
		} catch(Exception ex){
			Logger.getLogger(GestorActualizacionDB2i.class).error("Ha fallado al intentar crear la tabla DATABASECHANGELOGLOCK",ex);
		} finally { 
			XPersistence.commit();
		}
	}
	
	private static final String SQL_EXISTS_SCHEMA = "SELECT COUNT(*) FROM SYSIBM.SQLSCHEMAS where TABLE_SCHEM = '%s'";
	
	private static final String SQL_CREATE_SCHEMA = "CREATE COLLECTION %s";
	
	public static void crearBiblioteca(String schema){
		try{
			Query query = XPersistence.getManager().createNativeQuery(String.format(SQL_EXISTS_SCHEMA, schema));
			
			boolean existe = ((Number)query.getSingleResult()).intValue() != 0 ;
			
			if(!existe){
				query = XPersistence.getManager().createNativeQuery(String.format(SQL_CREATE_SCHEMA,schema));
				
				query.executeUpdate();
			}
			
		} catch(Exception ex){
			
			Logger.getLogger(GestorActualizacionDB2i.class).error("Ha fallado al intentar crear la biblioteca " + schema,ex);
			
		} finally { 
			
			XPersistence.commit();
		}
	}
	
}
