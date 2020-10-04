package com.gestion400.g4genwrapper.acciones.wrapper;

import com.gestion400.g4genwrapper.modelo.*;
import com.gswrapper.modelo.vista.*;
import com.gswrapper.modelo.wrapper.*;

public class BorrarMunicipioWrapper extends accionBaseMunicipio {
	
	public BorrarMunicipioWrapper(GSAWrapper3270 wrapper, Pantalla pantalla) {

		super(wrapper, pantalla);
	}

	@Override
	public Municipio ejecutar(Municipio municipio) {
	
		accederDetalleMunicipio(municipio);
		
		getWrapper().leerPantalla();
		
		getWrapper().ejecutarMandato(Mandato.F2);
		
		getWrapper().leerPantalla();
		
		if(esPantalaActual(PANTALLA_DIALOGO_MUNICIPIO)) {
			
			actualizarPantalla(PANTALLA_DIALOGO_MUNICIPIO);
			
			municipio.setEliminado(true);
		}
		
		return municipio;
	}
}
