package com.gestion400.g4genwrapper.acciones;

import com.aeat.valida.*;
import com.gestion400.dao.*;
import com.gestion400.g4genwrapper.modelo.*;
import com.gswrapper.modelo.wrapper.*;

public class GuardarTercero extends WrapperSaveAction {

	private static final String KEY_ACCION = "guardarTercero";

	@Override
	protected boolean validar() {
		
		return validarNif();
	}

	@Override
	protected void guardar(GSAWrapper3270 wrapper) throws Exception {
		
		String nif = getView().getValueString(BuscarTercero.KEY_ID);
		
		Tercero tercero = TerceroDao.INSTANCE.findTercero(nif);
		
		wrapper.ejecutarAccion(KEY_ACCION, tercero);
	}
	

	private boolean validarNif() {
		
		String nif = getView().getValueString(BuscarTercero.KEY_ID);
		
		Validador validadorNif = new Validador();
		
		boolean nifValido = validadorNif.checkNif(nif) > 0;
		
		if(!nifValido) addError("nif_invalido", nif);
		
		return nifValido;
	}
}
