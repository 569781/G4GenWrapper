package com.gestion400.g4genwrapper.acciones.wrapper;

import org.apache.log4j.*;

import com.gestion400.g4genwrapper.modelo.*;
import com.gswrapper.excepciones.*;
import com.gswrapper.modelo.vista.*;
import com.gswrapper.modelo.wrapper.*;

public class LeerTerceroWrapper extends AccionBaseTercero  {
	
	private static final Logger LOGGER = Logger.getLogger(LeerTerceroWrapper.class);
	
	private static final String KEY_ACCION_LEER_DOMICILIO = "leerDomicilio";

	public LeerTerceroWrapper(GSAWrapper3270 wrapper, Pantalla pantalla) {
		
		super(wrapper, pantalla);
	}

	@Override
	public Tercero ejecutar(Tercero tercero) {

		Tercero terceroWrapper = null;
		
		if(accederDetalleTercero(tercero)) {
			
			getWrapper().leerPantalla();
			
			terceroWrapper = leerTercero();

			terceroWrapper.setDomicilio(leerDomicilio(tercero));
		}
		
		return terceroWrapper;
	}
	
	private Tercero leerTercero() {
		
		return getObject(Tercero.class);
	}
	
	private Domicilio leerDomicilio(Tercero tercero) {
		
		try {
			
			return (Domicilio) ejecutarAccion(KEY_ACCION_LEER_DOMICILIO, tercero);
			
		} catch (IllegalScreenException | IllegalActionExcepcion e) {
		
			LOGGER.error("Error al ejecutar la accion " + KEY_ACCION_LEER_DOMICILIO ,e);
			
			actualizarPantalla(PANTALLA_TERCERO);
			
			return null;
		}
	}
}
