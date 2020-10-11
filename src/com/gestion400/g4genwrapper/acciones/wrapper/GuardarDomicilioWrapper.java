package com.gestion400.g4genwrapper.acciones.wrapper;

import org.openxava.util.*;

import com.gestion400.g4genwrapper.modelo.*;
import com.gswrapper.control.*;
import com.gswrapper.modelo.vista.*;
import com.gswrapper.modelo.wrapper.*;
import com.gswrapper.util.*;

public class GuardarDomicilioWrapper extends AccionBase<Domicilio, Domicilio>{

	public GuardarDomicilioWrapper(GSAWrapper3270 wrapper, Pantalla pantalla) {
		
		super(wrapper, pantalla);
	}

	@Override
	public Domicilio ejecutar(Domicilio domicilio) {
		
		getWrapper().leerPantalla();
		
		getWrapper().desplazarCursor();
	
		escribir(Strings.fix(getValueStrign(domicilio.getNumero()), 4, Align.RIGHT, '0'));
		
		escribir(Strings.fix(domicilio.getBis(), 1, Align.LEFT));
		
		escribir(Strings.fix(getValueStrign(domicilio.getNumero2()), 4, Align.RIGHT, '0'));
		
		escribir(Strings.fix(domicilio.getBis2(), 1, Align.LEFT));
		
		escribir(Strings.fix(getValueStrign(domicilio.getKilometro()).replace('.', ','), 9, Align.LEFT));
		
		escribir(Strings.fix(domicilio.getBloque(), 2, Align.LEFT));
		
		escribir(Strings.fix(domicilio.getPortal(), 2, Align.LEFT));
		
		escribir(Strings.fix(domicilio.getEscalera(), 2, Align.LEFT));
		
		escribir(Strings.fix(domicilio.getPlanta(), 3, Align.LEFT));
		
		escribir(Strings.fix(domicilio.getPuerta(), 4, Align.LEFT));
	
		escribir(Strings.fix(domicilio.getVia(), 5, Align.LEFT));
		
		escribir(Strings.fix(domicilio.getDenominacion(), 25, Align.LEFT));
		
		escribirInformacionProvincia(domicilio);
		
		escribirInformacionMunicipio(domicilio);
		
		escribir(Strings.fix(getValueStrign(domicilio.getCodigoPostal()) , 5, Align.RIGHT, '0'));
		
		escribir(Strings.fix(domicilio.getRefCatastralManzana(), 7, Align.LEFT), 3);
		
		escribir(Strings.fix(domicilio.getRefCatastralCentro(), 7, Align.LEFT));
		
		escribir(Strings.fix(getValueStrign(domicilio.getRefCatastralCargo()), 4, Align.RIGHT, '0'));
		
		escribir(Strings.fix(domicilio.getDigitoControl1(), 1, Align.LEFT));
		
		escribir(Strings.fix(domicilio.getDigitoControl2(), 1, Align.LEFT));
		
		getWrapper().leerPantalla();
		
		return domicilio;
	}
	
	private void escribirInformacionProvincia(Domicilio domicilio) {
		
		String codigoProvincia = "0";
		
		if(domicilio.getProvincia() != null) {
			
			codigoProvincia = getValueStrign(domicilio.getProvincia().getCodigo());
		}
		
		escribir(Strings.fix(codigoProvincia, 2, Align.RIGHT, '0'), 4);
	}
	
	
	private void escribirInformacionMunicipio(Domicilio domicilio) {
		
		String codigoMunicipio = "0";
		
		String descripcionMunicipio = "";
		
		if(domicilio.getMunicipio() != null) {
			
			codigoMunicipio = getValueStrign(domicilio.getMunicipio().getCodigo());
			
			descripcionMunicipio = getValueStrign(domicilio.getMunicipio().getDescripcion());
		}
		
		escribir(Strings.fix(codigoMunicipio, 3, Align.RIGHT, '0'));
		
		escribir(Strings.fix(descripcionMunicipio, 50, Align.LEFT));
	}
	
	private String getValueStrign(Object valor) {
		
		if(!Condition.empty(valor)) {
			
			return valor.toString();
		}
		
		return "";
	}
	
	private void escribir(Object valor) {
		
		escribir(valor, 0);
	}
	
	private void escribir(Object valor, int posicion) {
		
		getWrapper().escribir(valor.toString(), posicion);
	}

}
