package com.gestion400.g4genwrapper.acciones.wrapper;

import com.gestion400.g4genwrapper.modelo.*;
import com.gswrapper.control.*;
import com.gswrapper.modelo.vista.*;
import com.gswrapper.modelo.wrapper.*;

public abstract class AccionBaseProvincia extends AccionBase<Boolean, Provincia>{
	
	protected static final String PANTALLA_DETALLE_PROVINCIA = "DetalleProvincia";
	
	protected static final String PANTALLA_DIALOGO_PROVINCIA = "DialogoProvincia";

	public AccionBaseProvincia(GSAWrapper3270 wrapper, Pantalla pantalla) {
		
		super(wrapper, pantalla);
	}

	protected void accederDetalleProvincia(Provincia provincia) {
		
		getWrapper().escribir(String.valueOf(provincia.getCodigo()));
		
		confirmar(PANTALLA_DETALLE_PROVINCIA);
	}
}
