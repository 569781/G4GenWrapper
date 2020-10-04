package com.gestion400.g4genwrapper.acciones;

import com.gestion400.dao.*;
import com.gestion400.g4genwrapper.modelo.*;
import com.gswrapper.modelo.wrapper.*;
import com.gswrapper.util.*;

public class GuardarMunicipio extends WrapperSaveAction {

	private static final String KEY_ACCION = "guardarMunicipio";
	
	public static final String KEY_CODIGO_PROVINCIA = "provincia.codigo";
	
	public static final String KEY_CODIGO_MUNICIPIO = "codigo";
	
	private int codigoProvincia;
	
	private int codigoMunicipio;
	
	@Override
	protected boolean validar() {
		
		codigoProvincia = getView().getValueInt(KEY_CODIGO_PROVINCIA);
		
		codigoMunicipio = getView().getValueInt(KEY_CODIGO_MUNICIPIO);
		
		return !Condition.empty(codigoProvincia) && !Condition.empty(codigoMunicipio);
	}

	@Override
	protected void guardar(GSAWrapper3270 wrapper) throws Exception {
		
		Municipio municipio = MunicipioDao.INSTANCE.find(codigoProvincia, codigoMunicipio);
		
		wrapper.ejecutarAccion(KEY_ACCION, municipio);
	}

}
