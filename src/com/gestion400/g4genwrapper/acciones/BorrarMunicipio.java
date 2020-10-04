package com.gestion400.g4genwrapper.acciones;

import com.gestion400.dao.*;
import com.gestion400.g4genwrapper.modelo.*;
import com.gswrapper.modelo.wrapper.*;

public class BorrarMunicipio extends WrapperDeleteAction {
	
	private static final String KEY_ACCION = "borrarMunicipio";
	
	@Override
	protected boolean borrar(GSAWrapper3270 wrapper) throws Exception {
		
		int codigoProvincia = getView().getValueInt(GuardarMunicipio.KEY_CODIGO_PROVINCIA);
		
		int codigoMunicipio = getView().getValueInt(GuardarMunicipio.KEY_CODIGO_MUNICIPIO);
		
		Municipio municipio = MunicipioDao.INSTANCE.find(codigoProvincia, codigoMunicipio);
		
		wrapper.ejecutarAccion(KEY_ACCION, municipio);
		
		return municipio.isEliminado();
	}
}
