package com.gestion400.g4genwrapper.acciones.wrapper;

import com.gestion400.g4genwrapper.modelo.*;
import com.gswrapper.modelo.vista.*;
import com.gswrapper.modelo.wrapper.*;

public class LeerMunicipioWrapper extends accionBaseMunicipio {
	
	public LeerMunicipioWrapper(GSAWrapper3270 wrapper, Pantalla pantalla) {
		
		super(wrapper, pantalla);
	}

	@Override
	public Municipio ejecutar(Municipio municipio) {
		
		accederDetalleMunicipio(municipio);
		
		getWrapper().leerPantalla();
		
		return getObject(municipio.getClass());
	}
}
