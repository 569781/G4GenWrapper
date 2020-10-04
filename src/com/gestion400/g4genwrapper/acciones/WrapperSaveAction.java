package com.gestion400.g4genwrapper.acciones;

import org.openxava.actions.*;
import org.openxava.validators.*;

import com.gestion400.util.*;
import com.gswrapper.excepciones.*;
import com.gswrapper.modelo.wrapper.*;

public abstract class WrapperSaveAction extends SaveAction {
	
	private GSAWrapper3270 wrapper;
	
	private String idSesion;
	
	@Override
	public void execute() throws Exception {
		
		idSesion = getRequest().getSession().getId();
		
		wrapper = WrapperUtil.INSTANCE.getWrapper(idSesion);
		
		if(validar()) {
			
			super.execute();
			
			if(getErrors().isEmpty()) guardarEntidad();
		}
	}
	
	protected abstract boolean validar();
	
	protected abstract void guardar(GSAWrapper3270 wrapper) throws Exception;
	
	private void guardarEntidad() throws Exception {
		
		try {
			
			guardar(wrapper);
			
		} catch (IllegalScreenException e) {
			
			WrapperUtil.INSTANCE.tratarExcepcion(idSesion, ConstantesAplicacion.KEY_ERROR_PANTALLA_NO_ESPERADA);
			
		} catch (IllegalActionExcepcion e) {
			
			throw new ValidationException(ConstantesAplicacion.KEY_ERROR_ACCION_NO_ENCONTRADA);
			
		} catch (NoConnectionExcepcion e) {
			
			WrapperUtil.INSTANCE.tratarExcepcion(idSesion, ConstantesAplicacion.KEY_ERROR_SIN_CONEXION);
		}
	}

}
