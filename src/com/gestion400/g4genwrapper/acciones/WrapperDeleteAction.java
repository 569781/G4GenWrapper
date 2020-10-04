package com.gestion400.g4genwrapper.acciones;

import org.openxava.actions.*;
import org.openxava.validators.*;

import com.gestion400.util.*;
import com.gswrapper.excepciones.*;
import com.gswrapper.modelo.wrapper.*;

public abstract class WrapperDeleteAction extends DeleteAction {
	
	private GSAWrapper3270 wrapper;
	
	private String idSesion;

	@Override
	public void execute() throws Exception {
		
		setUp();
		
		if(borrarEntidad()) super.execute();
	}
	
	private void setUp() {
		
		idSesion = getRequest().getSession().getId();
		
		wrapper = WrapperUtil.INSTANCE.getWrapper(idSesion);
	}
	
	protected abstract boolean borrar(GSAWrapper3270 wrapper) throws Exception;
	
	private boolean borrarEntidad() throws Exception {
		
		try {
			
			return borrar(wrapper);
			
		} catch (IllegalScreenException e) {
			
			WrapperUtil.INSTANCE.tratarExcepcion(idSesion, ConstantesAplicacion.KEY_ERROR_PANTALLA_NO_ESPERADA);
			
		} catch (IllegalActionExcepcion e) {
			
			throw new ValidationException(ConstantesAplicacion.KEY_ERROR_ACCION_NO_ENCONTRADA);
			
		} catch (NoConnectionExcepcion e) {
			
			WrapperUtil.INSTANCE.tratarExcepcion(idSesion, ConstantesAplicacion.KEY_ERROR_SIN_CONEXION);
		}
		
		return false;
	}
}
