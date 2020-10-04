package com.gestion400.g4genwrapper.acciones;

import com.gestion400.dao.*;
import com.gestion400.g4genwrapper.modelo.*;
import com.gestion400.util.*;
import com.gswrapper.modelo.wrapper.*;

public class BuscarMunicipio extends WrapperSearchAction {

	private static final String KEY_ACCION = "leerMunicipio";
	
	private ViewUtil viewUtil = ViewUtil.INSTANCE;
	
	@Override
	protected void buscar(GSAWrapper3270 wrapper) throws Exception {
		
		int codigoProvincia = getView().getValueInt(GuardarMunicipio.KEY_CODIGO_PROVINCIA);
		
		int codigoMunicipio = getView().getValueInt(GuardarMunicipio.KEY_CODIGO_MUNICIPIO);
		
		Municipio municipio = MunicipioDao.INSTANCE.find(codigoProvincia, codigoMunicipio);
		
		if(municipio != null && !municipio.isActualizado()) {
			
			setValoresMunicipio((Municipio) wrapper.ejecutarAccion(KEY_ACCION, municipio));
		}
	}
	
	private void setValoresMunicipio(Municipio municipioWrapper) {
		
		if(municipioWrapper != null) {
			
			viewUtil.setValue(getView(), "codigoPostal", municipioWrapper.getCodigoPostal());
		}
	}

}
