package com.gestion400.g4genwrapper.acciones.wrapper;

import com.gestion400.g4genwrapper.modelo.*;
import com.gestion400.util.*;
import com.gswrapper.control.*;
import com.gswrapper.modelo.vista.*;
import com.gswrapper.modelo.wrapper.*;

public abstract class AccionBaseTercero extends AccionBase<Tercero, Tercero>{

	protected static final String PANTALLA_TERCERO = "DetalleTercero";
	
	protected static final String PANTALLA_DIALOGO_TERCERO = "DialogoTercero";
	
	
	public AccionBaseTercero(GSAWrapper3270 wrapper, Pantalla pantalla) {
		
		super(wrapper, pantalla);
	}

	
	protected boolean accederDetalleTercero(Tercero tercero) {

		String razonSocial = Util.cut(tercero.getRazonSocial(), 29);
		
		getWrapper().escribir(razonSocial);
		
		getWrapper().escribir(tercero.getNif(), 8);
		
		getWrapper().leerPantalla();
		
		return confirmar(PANTALLA_TERCERO);
	}
}
