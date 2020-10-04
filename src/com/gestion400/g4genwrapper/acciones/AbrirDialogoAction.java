package com.gestion400.g4genwrapper.acciones;

import org.openxava.actions.*;
import org.openxava.util.*;

public class AbrirDialogoAction extends ViewBaseAction {
	
private String controlador;
	
	private String modelo;
	
	private String vista;
	
	@Override
	public void execute() throws Exception {
		
		showDialog();
		
		setModelo();
		
		setVista();
		
		setControladores();
	}
	
	private void setModelo() {
		
		if(!Is.empty(modelo)) getView().setModelName(modelo);
	}
	
	private void setVista() {
		
		if(!Is.empty(vista)) getView().setViewName(vista);
	}
	
	private void setControladores() {
		
		if(!Is.empty(controlador)) setControllers(controlador);
	}

	public void setControlador(String controlador) {
		this.controlador = controlador;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public void setVista(String vista) {
		this.vista = vista;
	}
}
