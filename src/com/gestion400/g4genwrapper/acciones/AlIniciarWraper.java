package com.gestion400.g4genwrapper.acciones;

import java.io.*;

import org.apache.log4j.*;
import org.openxava.actions.*;
import org.openxava.util.*;
import org.openxava.validators.*;

import com.gestion400.util.*;
import com.gestion400.util.WrapperFactory.*;
import com.gestion400.wizard.Usuario;
import com.gswrapper.excepciones.*;
import com.gswrapper.modelo.wrapper.*;

public class AlIniciarWraper extends BaseAction {
	
	private static final Logger LOGGER = Logger.getLogger(AlIniciarWraper.class);
			
	private WrapperManager manager = WrapperManager.getInstance();
	
	private static final String MENSAJE_NUEVA_CONEXION = "Iniciando conexión para %s";
	
	private static final String ERROR_RUTA_EMULADOR = "Ha sido imposible encontrar el emulador en %s";
	private static final String ERROR_CONEXION = "Se ha perdido la conexión con el servidor";
	private static final String ERROR_CARGAR_PANTALLAS = "Ha ocurrido un error al intentar cargar la configuración de las pantallas";
	private static final String ERROR_ACCEDER_PANTALLA = "Error al intentar acceder a la pantalla %s";
	

	@Override
	public void execute() throws Exception {
		
		String idSesion = getRequest().getSession().getId();
		
		if(!existeWrapper(idSesion)) {
			
			Usuario usuario = UsuarioUtil.getInstance().recuperarUsuarioSesion(getRequest());
			
			LOGGER.info(String.format(MENSAJE_NUEVA_CONEXION, usuario.getId()));
			
			inicializarWrapper(idSesion, usuario);
		}
	}
	
	
	private boolean existeWrapper(String id) {
		
		return manager.existeWrapper(id);
	}
	
	
	private void inicializarWrapper(String idSesion, Usuario usuario) {
		
		try {
			
			GSAWrapper3270 wrapper = (GSAWrapper3270) manager.anyadirWrapper(idSesion, TipoWrapper.GSA);
			
			wrapper.cargarPantallasXML(getPathPantallas());
			
			conectar(idSesion, wrapper);
			
			login(idSesion, usuario, wrapper);
			
			accederMenuAplicaciones(idSesion, wrapper);
			
		} catch (IOException e) {

			WrapperUtil.INSTANCE.tratarExcepcion(idSesion, String.format(ERROR_RUTA_EMULADOR, ConstantesAplicacion.PATH_EMULADOR), e);
			
		} catch (NoConnectionExcepcion e) {
			
			WrapperUtil.INSTANCE.tratarExcepcion(idSesion, ERROR_CONEXION);
			
		} catch (ClassNotFoundException e) {
			
			WrapperUtil.INSTANCE.tratarExcepcion(idSesion, ERROR_CARGAR_PANTALLAS, e);
		}
	}
	
	
	private String getPathPantallas() {
		
		return getClass().getClassLoader().getResource(ConstantesAplicacion.XML_PANTALLAS).getPath();
	}
	
	
	private static final String ERROR_CONEXION_INCORRECTA = "Imposible conectar a %s:%d";
	
	private static final String KEY_IP = "IP_AS400";
	
	private static final String KEY_PUERTO = "PUERTO_AS400";
	
	private void conectar(String idSesion, GSAWrapper3270 wrapper) {
		
		String ip = ParametroUtil.getParametroString(KEY_IP, "");
		
		int puerto = ParametroUtil.getParametroInteger(KEY_PUERTO, 0);
		
		boolean conectado = wrapper.conectar(ip, puerto);
		
		if(!conectado) {
			
			WrapperUtil.INSTANCE.tratarExcepcion(idSesion, String.format(ERROR_CONEXION_INCORRECTA, ip, puerto));
		}
	}
	
	private static final String KEY_MENU_PRINCIPAL = "MAIN";
	
	private void login(String idSesion, Usuario usuario, GSAWrapper3270 wrapper) {
		
		String userAs400 = usuario.getUsuarioAs400();
		
		String password = usuario.getPasswordAs400();
		
		validarUsuario(idSesion, userAs400, password);

		boolean logueado = wrapper.login(userAs400, password, KEY_MENU_PRINCIPAL, 3);
	
		validarLogin(idSesion, logueado);
	}
	
	
	private static final String KEY_ERROR_LOGIN_VACIO = "AlIniciarWraper.error_login_vacio";
	
	private void validarUsuario(String idSesion, String usuario, String password) {
		
		if(Is.empty(usuario) || Is.empty(password)) {
			
			WrapperUtil.INSTANCE.tratarExcepcion(idSesion, KEY_ERROR_LOGIN_VACIO, new ValidationException(KEY_ERROR_LOGIN_VACIO));
		}
	}
	
	
	private static final String KEY_ERROR_LOGIN_INCORRECTO = "AlIniciarWraper.error_login_incorrecto";
	
	private void validarLogin(String idSesion, boolean logueado) {
		
		if(!logueado) {

			WrapperUtil.INSTANCE.tratarExcepcion(idSesion, KEY_ERROR_LOGIN_INCORRECTO, new ValidationException(KEY_ERROR_LOGIN_VACIO));
		}
	}
	
	
	private static final String KEY_PANTALLA_APLICACIONES = "GenMenu";
	
	private void accederMenuAplicaciones(String idSesion, GSAWrapper3270 wrapper) {
		
		try {
			
			wrapper.abrirPantalla(KEY_PANTALLA_APLICACIONES);
			
		} catch (IllegalScreenException e) {
			
			WrapperUtil.INSTANCE.tratarExcepcion(idSesion, String.format(ERROR_ACCEDER_PANTALLA, KEY_PANTALLA_APLICACIONES));
		}
	}
}
