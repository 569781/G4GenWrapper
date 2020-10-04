package com.gestion400.g4genwrapper.acciones;

import org.openxava.actions.*;

import com.gestion400.util.*;

public class AlIniciarAnuncios extends ViewBaseAction{

	@Override
	public void execute() throws Exception {
		
		getRequest().setAttribute(ConstantesAplicacion.TAG_TABLON, TablonDeAnuncios.getInstance());
	}
}
