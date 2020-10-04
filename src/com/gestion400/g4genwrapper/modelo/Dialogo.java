package com.gestion400.g4genwrapper.modelo;

import org.openxava.annotations.Required;
import org.openxava.annotations.Stereotype;
import org.openxava.annotations.View;
import org.openxava.annotations.Views;

@Views({
	@View(name = "restablecerPassword", members = "password;passwordRepetida;restablecerPassword")
})

public class Dialogo {

	@Stereotype("PASSWORD")
	@Required
	private String password;
	
	@Stereotype("PASSWORD")
	@Required
	private String passwordRepetida;
	
	private boolean restablecerPassword;

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPasswordRepetida() {
		return passwordRepetida;
	}

	public void setPasswordRepetida(String passwordRepetida) {
		this.passwordRepetida = passwordRepetida;
	}

	public boolean isRestablecerPassword() {
		return restablecerPassword;
	}

	public void setRestablecerPassword(boolean restablecerPassword) {
		this.restablecerPassword = restablecerPassword;
	}
}
