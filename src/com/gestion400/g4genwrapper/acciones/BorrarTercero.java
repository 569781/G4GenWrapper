package com.gestion400.g4genwrapper.acciones;

import com.gestion400.dao.*;
import com.gestion400.g4genwrapper.modelo.*;
import com.gswrapper.modelo.wrapper.*;

public class BorrarTercero extends WrapperDeleteAction {
	
	private static final String KEY_ACCION = "borrarTercero";

	@Override
	protected boolean borrar(GSAWrapper3270 wrapper) throws Exception {
		
		String nif = getView().getValueString(BuscarTercero.KEY_ID);
		
		Tercero tercero = TerceroDao.INSTANCE.findTercero(nif);
		
		wrapper.ejecutarAccion(KEY_ACCION, tercero);
		
		return tercero.isEliminado();
	}

}
