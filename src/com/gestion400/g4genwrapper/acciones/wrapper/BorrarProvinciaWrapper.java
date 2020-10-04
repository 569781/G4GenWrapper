package com.gestion400.g4genwrapper.acciones.wrapper;

import com.gestion400.g4genwrapper.modelo.*;
import com.gswrapper.modelo.vista.*;
import com.gswrapper.modelo.wrapper.*;

public class BorrarProvinciaWrapper extends AccionBaseProvincia {
	
	public BorrarProvinciaWrapper(GSAWrapper3270 wrapper, Pantalla pantalla) {
		
		super(wrapper, pantalla);
	}

	@Override
	public Boolean ejecutar(Provincia provincia) {
		
		accederDetalleProvincia(provincia);
		
		getWrapper().leerPantalla();
		
		getWrapper().ejecutarMandato(Mandato.F2);
		
		getWrapper().leerPantalla();
		
		boolean eliminado = esPantalaActual(PANTALLA_DIALOGO_PROVINCIA);
		
		if(eliminado) actualizarPantalla(PANTALLA_DIALOGO_PROVINCIA);
		
		return eliminado;	
	}
}
