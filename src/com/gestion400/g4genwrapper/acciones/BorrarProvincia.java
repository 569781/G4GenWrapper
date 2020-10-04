package com.gestion400.g4genwrapper.acciones;

import com.gestion400.dao.*;
import com.gestion400.g4genwrapper.modelo.*;
import com.gswrapper.modelo.wrapper.*;

public class BorrarProvincia extends WrapperDeleteAction {

	private static final String KEY_ACCION = "borrarProvincia";
	
	@Override
	protected boolean borrar(GSAWrapper3270 wrapper) throws Exception {
		
		int codigoProvincia = getView().getValueInt(GuardarProvincia.KEY_CODIGO_PROVINCIA);
		
		Provincia provincia = ProvinciaDao.INSTANCE.find(codigoProvincia);
		
		return (boolean) wrapper.ejecutarAccion(KEY_ACCION, provincia);
	}
	
}
