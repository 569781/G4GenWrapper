package com.gestion400.g4genwrapper.acciones.wrapper;

import com.gestion400.g4genwrapper.modelo.*;
import com.gswrapper.modelo.vista.*;
import com.gswrapper.modelo.wrapper.*;

public class GuardarMunicipioWrapper extends accionBaseMunicipio {

	public GuardarMunicipioWrapper(GSAWrapper3270 wrapper, Pantalla pantalla) {
		
		super(wrapper, pantalla);
	}

	@Override
	public Municipio ejecutar(Municipio municipio) {
	
		accederDetalleMunicipio(municipio);
		
		escribirCampos(municipio);
		
		return municipio;
	}
	
	private void escribirCampos(Municipio municipio) {
		
		getWrapper().escribir(municipio.getDescripcion(), 1);
		
		getWrapper().escribir(getCodigo(municipio.getCodigoPostal(), 5), 4);
		
		getWrapper().leerPantalla();
		
		boolean pantallaDetalle = confirmar(PANTALLA_DETALLE_MUNICIPIO);
		
		if(pantallaDetalle) {
			
			escribirCodigoDeControl();
			
		} else {
			
			actualizarPantalla(PANTALLA_DIALOGO_MUNICIPIO);
		}
	}
	
	private static final String CAMPO_COD_CONTROL = "digitoControl";
	
	private void escribirCodigoDeControl() {
		
		String digitoDeControl = getCampo(CAMPO_COD_CONTROL);
		
		System.out.println("EL DIGITO DE CONTROL ES : " + digitoDeControl);
		
		getWrapper().desbloquearTeclado();
		
		getWrapper().escribir(digitoDeControl);
		
		getWrapper().leerPantalla();
		
		confirmar(PANTALLA_DIALOGO_MUNICIPIO);
		
		getWrapper().leerPantalla();
	}

}
