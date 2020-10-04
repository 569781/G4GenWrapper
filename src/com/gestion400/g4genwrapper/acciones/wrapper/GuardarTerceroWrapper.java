package com.gestion400.g4genwrapper.acciones.wrapper;

import org.apache.log4j.*;
import org.openxava.util.*;

import com.gestion400.g4genwrapper.modelo.*;
import com.gswrapper.excepciones.*;
import com.gswrapper.modelo.vista.*;
import com.gswrapper.modelo.wrapper.*;

public class GuardarTerceroWrapper extends AccionBaseTercero{
	
	private static final Logger LOGGER = Logger.getLogger(GuardarTerceroWrapper.class);
	
	private static final String ACCION_GUARDAR_DOMICILIO = "guardarDomicilio";
	
	public GuardarTerceroWrapper(GSAWrapper3270 wrapper, Pantalla pantalla) {
		
		super(wrapper, pantalla);
	}

	@Override
	public Tercero ejecutar(Tercero tercero) {
		
		accederDetalle(tercero);
		
		escribirTercero(tercero);
		
		escribirDireccion(tercero);
		
		return tercero;
	}
	
	private void accederDetalle(Tercero tercero) {
		
		if(!accederDetalleTercero(tercero)) {
			
			abrir(PANTALLA_TERCERO);
		}
	}
	
	private void escribirTercero(Tercero tercero) {
		
		escribir(Strings.fix(tercero.getNif(), 10, Align.RIGHT), 1);
		
		escribir(tercero.getNaturaleza().ordinal() + 1);
		
		escribir(Strings.fix(tercero.getRazonSocial(), 60, Align.LEFT));
		
		escribir(tercero.getPais().getCodigo(), 2);
		
		escribir(Strings.fix(tercero.getNombre(), 20, Align.LEFT));
		
		escribir(Strings.fix(tercero.getPrimerApellido(), 25, Align.LEFT));
		
		escribir(Strings.fix(tercero.getSegundoApellido(), 25, Align.LEFT), 1);
		
		escribir(Strings.fix(tercero.getTelefono(), 15, Align.LEFT), 2);
		
		escribir(Strings.fix(tercero.getEmail(), 60, Align.LEFT), 1);
		
		getWrapper().leerPantalla();
		
		confirmar(ACCION_GUARDAR_DOMICILIO);
		
		getWrapper().leerPantalla();
	}
	
	private void escribirDireccion(Tercero tercero) {
		
		try {
			
			ejecutarAccion(ACCION_GUARDAR_DOMICILIO, tercero.getDomicilio(), PANTALLA_DIALOGO_TERCERO);
			
			actualizarPantalla(PANTALLA_DIALOGO_TERCERO);
			
		} catch (IllegalScreenException | IllegalActionExcepcion e) {
		
			LOGGER.error("Error al ejecutar " + ACCION_GUARDAR_DOMICILIO, e);
			
			confirmar();
		}
	}
	
	private void escribir(Object valor) {
		
		escribir(valor, 0);
	}
	
	private void escribir(Object valor, int posicion) {
		
		getWrapper().escribir(valor.toString(), posicion);
	}
}
