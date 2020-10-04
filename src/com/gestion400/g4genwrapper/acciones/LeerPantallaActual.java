package com.gestion400.g4genwrapper.acciones;

import org.openxava.actions.*;

import com.gestion400.util.*;
import com.gswrapper.control.*;

public class LeerPantallaActual extends ViewBaseAction{

	@Override
	public void execute() throws Exception {
		
		String idSesion = getRequest().getSession().getId();
		
		GSAWrapperX3270 wrapper = (GSAWrapperX3270) WrapperUtil.INSTANCE.getWrapper(idSesion);
		
		wrapper.leerPantalla();
	}
	
	

}
