package com.gestion400.g4genwrapper.acciones.wrapper;

import org.openxava.util.*;

import com.gestion400.g4genwrapper.modelo.*;
import com.gswrapper.control.*;
import com.gswrapper.modelo.vista.*;
import com.gswrapper.modelo.wrapper.*;

public abstract class accionBaseMunicipio extends AccionBase<Municipio, Municipio>{

	protected static final String PANTALLA_DIALOGO_MUNICIPIO = "DialogoMunicipio";
	
	protected static final String PANTALLA_DETALLE_MUNICIPIO = "DetalleMunicipio";
	
	public accionBaseMunicipio(GSAWrapper3270 wrapper, Pantalla pantalla) {
		
		super(wrapper, pantalla);
	}
	
	
	protected void accederDetalleMunicipio(Municipio municipio) {
		
		int codigoProvincia = municipio.getProvincia().getCodigo();
		
		int codigoMunicipio = municipio.getCodigo();
		
		getWrapper().escribir(getCodigo(codigoProvincia, 2));
		
		getWrapper().escribir(getCodigo(codigoMunicipio, 3), 2);
		
		confirmar(PANTALLA_DETALLE_MUNICIPIO);
	}
	
	
	protected String getCodigo(int codigo, int dimension) {
		
		String codigoString = String.valueOf(codigo);
		
		return Strings.fix(codigoString, dimension, Align.RIGHT, '0');
	}
	
}

