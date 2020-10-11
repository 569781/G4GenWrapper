package com.gestion400.g4genwrapper.acciones;

import java.text.*;
import java.util.*;

import org.openxava.view.*;

import com.gestion400.dao.*;
import com.gestion400.g4genwrapper.modelo.*;
import com.gestion400.g4genwrapper.modelo.Tercero.*;
import com.gestion400.util.*;
import com.gswrapper.modelo.wrapper.*;
import com.gswrapper.util.*;

public class BuscarTercero extends WrapperSearchAction {
	
	private static final String KEY_ACCION = "leerTercero";
											  
	public static final String KEY_ID = "nif";
	
	private ViewUtil viewUtil = ViewUtil.INSTANCE;
	
	@Override
	protected void buscar(GSAWrapper3270 wrapper) throws Exception {
		
		String nif = (String) getKeyValuesFromView().get(KEY_ID);

		Tercero tercero = TerceroDao.INSTANCE.findTercero(nif);
		
		if(tercero != null && !tercero.isActualizado()) {
			
			setValoresTercero((Tercero) wrapper.ejecutarAccion(KEY_ACCION, tercero));
		}
	}
	
	private void setValoresTercero(Tercero terceroWrapper) {
		
		if(terceroWrapper != null) {
			
			View view = getView();
			
			viewUtil.setValue(view, "nombre", terceroWrapper.getNombre());	
			viewUtil.setValue(view, "primerApellido", terceroWrapper.getPrimerApellido());	
			viewUtil.setValue(view, "segundoApellido", terceroWrapper.getSegundoApellido());
			viewUtil.setValue(view, "telefono", terceroWrapper.getTelefono());
			viewUtil.setValue(view, "email", terceroWrapper.getEmail());
			viewUtil.setValue(view, "pais.codigo", terceroWrapper.getCodigoPais());
			viewUtil.setValue(view, "naturaleza", getNaturalezaTercero(terceroWrapper));
			
			setFecha("fechaAlta", terceroWrapper.getFechaAltaString());
			setFecha("fechaBaja", terceroWrapper.getFechaBajaString());
			setFecha("fechaModificacion", terceroWrapper.getFechaModificacionString());
			
			setValoresDomicilio(terceroWrapper.getDomicilio());
		}
	}
	
	private Naturaleza getNaturalezaTercero(Tercero terceroWrapper) {
		
		Naturaleza naturaleza = Naturaleza.NIF;
		
		int codigoNaturaleza = Condition.evalNotEmpty(terceroWrapper.getCodigoNaturaleza(), 0);
		
		if(codigoNaturaleza > 0 && Naturaleza.values().length >= codigoNaturaleza) naturaleza =  Naturaleza.values()[codigoNaturaleza - 1];
		
		return naturaleza;
	}
	
	private static final String KEY_DOMICILIO = "domicilio.";
	
	private void setValoresDomicilio(Domicilio domicilio) {
		
		if(domicilio != null) {
			
			View view = getView();
			
			viewUtil.setValue(view, KEY_DOMICILIO + "via", domicilio.getVia());
			viewUtil.setValue(view, KEY_DOMICILIO + "denominacion", domicilio.getDenominacion());	
			viewUtil.setValue(view, KEY_DOMICILIO + "numero", domicilio.getNumero());
			viewUtil.setValue(view, KEY_DOMICILIO + "bis", domicilio.getBis());
			viewUtil.setValue(view, KEY_DOMICILIO + "numero2", domicilio.getNumero2());
			viewUtil.setValue(view, KEY_DOMICILIO + "bis2", domicilio.getBis2());	
			viewUtil.setValue(view, KEY_DOMICILIO + "kilometro", domicilio.getKilometro());
			viewUtil.setValue(view, KEY_DOMICILIO + "bloque", domicilio.getBloque());		
			viewUtil.setValue(view, KEY_DOMICILIO + "portal", domicilio.getPortal());		
			viewUtil.setValue(view, KEY_DOMICILIO + "escalera", domicilio.getEscalera());		
			viewUtil.setValue(view, KEY_DOMICILIO + "planta", domicilio.getPlanta());
			viewUtil.setValue(view, KEY_DOMICILIO + "puerta", domicilio.getPuerta());
			viewUtil.setValue(view, KEY_DOMICILIO + "codigoPostal", domicilio.getCodigoPostal());
			viewUtil.setValue(view, KEY_DOMICILIO + "refCatastralManzana", domicilio.getRefCatastralManzana());
			viewUtil.setValue(view, KEY_DOMICILIO + "refCatastralCentro", domicilio.getRefCatastralCentro());
			viewUtil.setValue(view, KEY_DOMICILIO + "refCatastralCargo", domicilio.getRefCatastralCargo());
			viewUtil.setValue(view, KEY_DOMICILIO + "digitoControl1", domicilio.getDigitoControl1());
			viewUtil.setValue(view, KEY_DOMICILIO + "digitoControl2", domicilio.getDigitoControl2());
			
			setProvinciaYMunicipio(domicilio);
		}
		
	}
	
	private void setFecha(String propiedad, String fechaString){
		
		if(!Condition.empty(fechaString)) {
			
			Date fecha = null;
			
			try {
				
				fecha = ConstantesAplicacion.FORMATO_FECHA_AS400.parse(fechaString);
				
			} catch (ParseException e) {
				
				fecha = null;
			}
			
			viewUtil.setValue(getView(), propiedad, fecha);
		}
	}
	
	private void setProvinciaYMunicipio(Domicilio domicilio) {
		
		int codigoProvincia = Condition.evalNotEmpty(domicilio.getCodigoProvincia(), 0);
				
		int codigoMunicipio = Condition.evalNotEmpty(domicilio.getCodigoMunicipio(), 0);
		
		Municipio municipio = MunicipioDao.INSTANCE.find(codigoProvincia, codigoMunicipio);
		
		if(municipio != null) {
			
			viewUtil.setValue(getView(), KEY_DOMICILIO + "provincia.codigo", codigoProvincia);
			
			viewUtil.setValue(getView(), KEY_DOMICILIO + "municipio.codigoProvincia", codigoProvincia);
			
			viewUtil.setValue(getView(), KEY_DOMICILIO + "municipio.codigo", codigoMunicipio);
		}
	}
}
