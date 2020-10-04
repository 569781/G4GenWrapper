package com.gestion400.seguridad.servlet;

import java.sql.*;

import javax.servlet.*;
import javax.servlet.http.*;

import org.apache.log4j.*;

import com.gestion400.bd.*;
import com.gestion400.util.*;

public class ActualizadorCambiosBD extends HttpServlet{
	
	private static final Logger LOGGER = Logger.getLogger(ActualizadorCambiosBD.class);
	
	private static final String RUTA_CHANGELOG = ConstantesAplicacion.NOMBRE_APLICACION + "_changelog.sql";
	
	private static final String MENSAJE_INICIO_LIQUIBASE = "Iniciando actualizador de cambios de base de datos";
	
	private static final String MENSAJE_ERROR_LIQUIBASE = "Ha fallado la actualización de cambios";
	
	private static final long serialVersionUID = 1L;

	@Override
	public void init(ServletConfig config) throws ServletException {
		
		actualizarCambiosBD();
	}
	
	private void actualizarCambiosBD(){
		
		try {
			
			LOGGER.info(MENSAJE_INICIO_LIQUIBASE);
			
			GestorActualizacionDB2i.actualizarBaseDatos(RUTA_CHANGELOG, ConstantesAplicacion.ESQUEMA_APLICACION);
			
		} catch (SQLException ex) {
			
			LOGGER.error(MENSAJE_ERROR_LIQUIBASE, ex);
		}
	}

}
