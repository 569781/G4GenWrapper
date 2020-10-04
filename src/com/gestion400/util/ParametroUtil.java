package com.gestion400.util;

import java.math.*;

import javax.persistence.*;

import org.apache.log4j.*;
import org.openxava.util.*;

import com.gestion400.bd.*;
import com.gestion400.g4genwrapper.modelo.*;



public class ParametroUtil {
	
	private static final Logger LOGGER = Logger.getLogger(ParametroUtil.class);
	
	private static final String SQL_PARAMETRO = "FROM Parametro where clave = :clave";
	
	public static synchronized Parametro findParametroByCodigo(String clave) {
		
		try(DBManager manager = PersistenceManager.getManager()){
			
			Query q = manager.createQuery(SQL_PARAMETRO);
			
			q.setParameter("clave", clave);

			return (Parametro) q.getSingleResult();
		
		} catch(NoResultException ex){
			
			return null;
		}
	}
	
	private static <T> T getParametro(Class<T> T ,String clave, T valorDefecto){
	
		T valor = valorDefecto;
		
		Parametro parametro = findParametroByCodigo(clave);
		
		if(parametro != null && !Is.empty(parametro.getValor())) {
			
			try {
				
				valor = T.getConstructor(String.class).newInstance(parametro.getValor());
				
			} catch (Exception e) {
				
				LOGGER.error("No existe el constructor String para clase " + 
						T.getClass().getName() + " , parámetro " + parametro.getValor() ,e);
				
				valor = valorDefecto;
			}
		}	

		return valor;
	}
	
	
	public static String getParametroString(String clave) {
		return getParametro(String.class, clave, "");
	}
	
	public static String getParametroString(String clave, String valorDefecto) {
		return getParametro(String.class, clave, valorDefecto);
	}
	
	public static BigDecimal getParametroBigDecimal(String clave) {
		return getParametro(BigDecimal.class, clave, new BigDecimal("0.00"));
	}
	
	public static BigDecimal getParametroBigDecimal(String clave, BigDecimal valorDefecto) {
		return getParametro(BigDecimal.class, clave, valorDefecto);
	}
	
	public static boolean getParametroBoolean(String clave) {
		return getParametro(Boolean.class, clave, false);
	}
	
	public static boolean getParametroBoolean(String clave, boolean valorDefecto) {	
		return getParametro(Boolean.class, clave, valorDefecto);
	}
	
	public static int getParametroInteger(String clave) {
		return getParametro(Integer.class, clave, 0);
	}
	
	public static int getParametroInteger(String clave, int valorDefecto) {
		return getParametro(Integer.class, clave, valorDefecto);
	}

}
