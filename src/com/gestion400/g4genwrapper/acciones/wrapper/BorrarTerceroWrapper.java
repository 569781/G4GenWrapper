package com.gestion400.g4genwrapper.acciones.wrapper;

import com.gestion400.g4genwrapper.modelo.*;
import com.gswrapper.modelo.vista.*;
import com.gswrapper.modelo.wrapper.*;

public class BorrarTerceroWrapper extends AccionBaseTercero {

	public BorrarTerceroWrapper(GSAWrapper3270 wrapper, Pantalla pantalla) {
		
		super(wrapper, pantalla);
	}

	@Override
	public Tercero ejecutar(Tercero tercero) {
	
		if(accederDetalleTercero(tercero)) {
				
			getWrapper().ejecutarMandato(Mandato.F2);
			
			getWrapper().ejecutarMandato(Mandato.F23);
			
			getWrapper().ejecutarMandato(Mandato.F12);
			
			tercero.setEliminado(true);
		}
		
		return tercero;
	}

}
