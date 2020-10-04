package com.gestion400.util;

import java.text.*;

public interface ConstantesAplicacion {
	
	public static final String ESQUEMA_APLICACION = "G4GENWRAPPER";
	
	public static final String NOMBRE_APLICACION = "G4GenWrapper";
	
	public static final String DESCRIPCION_APLICACION = "Mantenimiento general terceros";
	
	public static final String ENTIDAD = "Unizar";
	
	public static final String PAGINA_INICIO = "Inicio";
	
	public static final String PAGINA_LOGIN = "/jsp/login.jsp";
	
	public static final String PAGINA_CAMBIAR_PASSWORD = "/jsp/restablecerPassword.jsp";
	
	public static final String PAGINA_ACCESO_RESTRINGIDO = "/jsp/unauthorized.jsp";
	
	public static final String RUTA_PAGINA_INICIO = "/" + NOMBRE_APLICACION + "/modules/" + PAGINA_INICIO;
	
	public static final String RUTA_PAGINA_LOGIN = "/" + NOMBRE_APLICACION + PAGINA_LOGIN;
	
	public static final String TAG_TABLON = "tablon_anuncios";
	
	public static final DateFormat FORMATO_FECHA_ANUNCIO = new SimpleDateFormat("dd/MM/yyyy HH:mm");
	
	public static final DateFormat FORMATO_FECHA_AS400 = new SimpleDateFormat("dd.MM.yyyy");
	
	public static final String PATH_EMULADOR = "../../windows/ws3270.exe";
	
	public static final String XML_PANTALLAS = "menu.xml";
	
	public static final String KEY_ERROR_PANTALLA_NO_ESPERADA = "error_pantalla_no_esperada";
	
	public static final String KEY_ERROR_ACCION_NO_ENCONTRADA = "error_accion_no_encontrada";
	
	public static final String KEY_ERROR_SIN_CONEXION = "error_conexion";

}
