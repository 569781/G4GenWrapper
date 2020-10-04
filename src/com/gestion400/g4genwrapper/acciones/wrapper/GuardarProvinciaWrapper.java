package com.gestion400.g4genwrapper.acciones.wrapper;

import com.gestion400.g4genwrapper.modelo.*;
import com.gswrapper.modelo.vista.*;
import com.gswrapper.modelo.wrapper.*;

public class GuardarProvinciaWrapper extends AccionBaseProvincia {

	public GuardarProvinciaWrapper(GSAWrapper3270 wrapper, Pantalla pantalla) {
		
		super(wrapper, pantalla);
	}

	@Override
	public Boolean ejecutar(Provincia provincia) {
		
		accederDetalleProvincia(provincia);
		
		getWrapper().escribir(provincia.getDescripcion());
		
		confirmar();
		
		return true;
	}

}
