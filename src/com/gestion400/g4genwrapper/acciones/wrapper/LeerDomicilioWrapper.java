package com.gestion400.g4genwrapper.acciones.wrapper;

import com.gestion400.g4genwrapper.modelo.*;
import com.gswrapper.control.*;
import com.gswrapper.modelo.vista.*;
import com.gswrapper.modelo.wrapper.*;
import com.gswrapper.util.*;

public class LeerDomicilioWrapper extends AccionBase<Domicilio, Tercero>{
	
	private static final String PANTALLA_GESTION_DOMICILIO = "GestionDomicilioTercero";
	
	private static final String PANTALLA_DOMICILIO = "ConsultaDomicilioTercero";
	
	public LeerDomicilioWrapper(GSAWrapper3270 wrapper, Pantalla pantalla) {
		
		super(wrapper, pantalla);
	}

	@Override
	public Domicilio ejecutar(Tercero tercero) {
		
		Domicilio domicilio = null;
		
		if(existeDomicilio()) domicilio = leerDomicilio();
		
		return domicilio;
	}
	
	private static final String CAMPO_DIRECCION = "domicilio";
	
	private static final String DIRECCION_NO_ENCONTRADA = "NO ENCONTRADA";
	
	private boolean existeDomicilio() {
		
		String direccion = getCampo(CAMPO_DIRECCION);
		
		return !Condition.empty(direccion) && !direccion.contains(DIRECCION_NO_ENCONTRADA);
	}
	
	private Domicilio leerDomicilio() {
		
		Domicilio domicilio = null;
		
		if(abrir(PANTALLA_DOMICILIO)) {
			
			getWrapper().leerPantalla();
			
			domicilio = getObject(Domicilio.class);
			
		} else {
			
			actualizarPantalla(PANTALLA_GESTION_DOMICILIO);
		}
		
		return domicilio;
	}
}
