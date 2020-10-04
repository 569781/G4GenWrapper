package com.gestion400.g4genwrapper.acciones;

import org.openxava.actions.ViewBaseAction;
import org.openxava.util.Is;
import org.openxava.validators.ValidationException;

import com.gestion400.dao.*;
import com.gestion400.seguridad.servlet.*;
import com.gestion400.util.*;

public class RestablecerPassword extends ViewBaseAction {
	
	private static final String KEY_PASSWORD = "password";
	
	private static final String KEY_PASSWORD_REPETIDA = "passwordRepetida";
	
	private static final String KEY_PASSWORD_RESTABLECER = "restablecerPassword";
	
	private static final String KEY_ID_USUARIO = "id";

	private String password;
	private String passwordRepetida;
	private boolean restablecer;
	
	private String idUsuario;
	
	@Override
	public void execute() throws Exception {
		
		setUp();
		
		validarParametros();
		
		restablecerPassword();
	}
	
	private void setUp() {
		
		idUsuario = getPreviousView().getValueString(KEY_ID_USUARIO);
		
		password = getView().getValueString(KEY_PASSWORD);
		
		passwordRepetida = getView().getValueString(KEY_PASSWORD_REPETIDA);
		
		restablecer = (boolean) getView().getValue(KEY_PASSWORD_RESTABLECER);
	}
	
	private void validarParametros() {
		
		passwordNoVacias();
		
		passwordDimensioCorrecta();
		
		passwordIguales();
		
		usuarioValido();
	}
	
	private void restablecerPassword() {
		
		String passwordEncriptada = PasswordUtil.getInstance().encriptar(password);
		
		UsuarioDao.INSTANCE.cambiarPassword(idUsuario, passwordEncriptada, restablecer);
		
		closeDialog();
		
		addMessage("RestablecerPassword.exito");
	}
	
	private void passwordNoVacias() {
		
		if(Is.empty(password) || Is.empty(passwordRepetida)) throw new ValidationException("RestablecerPassword.passwordVacia");
	}
	
	
	private void passwordDimensioCorrecta() {
		
		int dimensionMinima = RestablecerPasswordServlet.MIN_LENGTH_PASSWORD;
		
		if(password.length() < dimensionMinima ) throw new ValidationException("RestablecerPassword.dimensionIncorrecta");
	}
	
	private void passwordIguales() {
		
		if(!password.equals(passwordRepetida)) throw new ValidationException("RestablecerPassword.passwordNoCoindice");
	}
	
	private void usuarioValido() {
		
		if(Is.empty(idUsuario)) throw new ValidationException("RestablecerPassword.UsuarioNoEncontrado");
	}
}
