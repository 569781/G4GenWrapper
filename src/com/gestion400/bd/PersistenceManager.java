package com.gestion400.bd;

import org.openxava.jpa.XPersistence;

public class PersistenceManager {
	
	public static DBManager getManager() {
		return new DBManager(XPersistence.getManager());
	}
	
}
