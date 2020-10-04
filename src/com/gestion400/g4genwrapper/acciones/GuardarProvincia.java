package com.gestion400.g4genwrapper.acciones;

import com.gestion400.dao.*;
import com.gestion400.g4genwrapper.modelo.*;
import com.gswrapper.modelo.wrapper.*;
import com.gswrapper.util.*;

public class GuardarProvincia extends WrapperSaveAction {

	private static final String KEY_ACCION = "guardarProvincia";
	
	public static final String KEY_CODIGO_PROVINCIA = "codigo";
	
	private int codigoProvincia;
	
	@Override
	protected boolean validar() {
		
		codigoProvincia = getView().getValueInt(KEY_CODIGO_PROVINCIA);

		return !Condition.empty(codigoProvincia);
	}

	@Override
	protected void guardar(GSAWrapper3270 wrapper) throws Exception {
		
		Provincia provincia = ProvinciaDao.INSTANCE.find(codigoProvincia);
		
		wrapper.ejecutarAccion(KEY_ACCION, provincia);
	}
}
