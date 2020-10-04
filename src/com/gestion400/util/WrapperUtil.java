package com.gestion400.util;

import org.apache.log4j.*;
import org.openxava.util.*;
import org.openxava.validators.*;

import com.gestion400.util.WrapperFactory.*;
import com.gswrapper.modelo.wrapper.*;
import com.gswrapper.util.*;

public enum WrapperUtil {
	
	INSTANCE;
	
	private static final Logger LOGGER = Logger.getLogger(WrapperUtil.class);
	
	private static final String KEY_ERROR_WRAPPER_NULO = "WrapperUtil.error_wrapper_no_recuperado";
	
	public GSAWrapper3270 getWrapper(String idSesion) {
		
		GSAWrapper3270 wrapper = null;
		
		wrapper = WrapperManager.getInstance().getWrapper(TipoWrapper.GSA.clase(), idSesion);
		
		if(Is.empty(wrapper)) throw new ValidationException(KEY_ERROR_WRAPPER_NULO);
		
		return wrapper;
	}

	public void tratarExcepcion(String idSesion, String mensajeError) {
		
		tratarExcepcion(idSesion, mensajeError, null);
	}
	
	public void tratarExcepcion(String idSesion, String mensajeError, Throwable e) {
		
		if(!Condition.empty(e)) LOGGER.error(mensajeError, e);
		
		desconectar(idSesion);
		
		throw new ValidationException(mensajeError);
	}
	
	
	public void desconectar(String idSesion) {
		
		WrapperManager.getInstance().borrarWrapper(idSesion);
	}
}
