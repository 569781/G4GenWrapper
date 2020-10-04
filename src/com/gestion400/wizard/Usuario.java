package com.gestion400.wizard;

import java.util.*;

import javax.persistence.*;

import org.openxava.annotations.*;

import com.gestion400.convertidores.*;
import com.gestion400.util.*;

@View(members = "General{id, superUsuario; nombre; primerApellido, segundoApellido; correo; usuarioAs400, passwordAs400}, Roles{roles}")
@Tab(properties = "id, nombre, primerApellido, segundoApellido, correo")

@Entity
@Table(schema = ConstantesAplicacion.ESQUEMA_APLICACION, name = "USUARIO")
@NamedQuery(name = "Usuario.validarPassword", query = "FROM Usuario u  WHERE UPPER(u.id) = :nif AND u.password = :password")
public class Usuario {
	
	@Id
	@Required
	@Column(name = "NIF",length = 9)
	@Convert(converter = UpperCaseConverter.class)
	private String id;
	
	@Required
	@Column(name = "NOMBRE",length = 25)
	private String nombre;
	
	@Required
	@Column(name = "PRIMERAPELLIDO",length = 25)
	private String primerApellido;

	@Column(name = "SEGUNDOAPELLIDO",length = 25)
	private String segundoApellido;
	
	@Column(name = "EMAIL",length = 60)
	private String correo;
	
	@Column(name = "SUPERUSUARIO")
	private boolean superUsuario;
	
	@Column(name = "ACTUALIZARPASSWORD")
	private boolean cambiarPassword;
	
	@Column(name = "PASSWORD")
	private String password;
	
	@Required
	@Column(name = "USUARIOAS400",length = 10)
	private String usuarioAs400;
	
	@Stereotype("PASSWORD")
	@Column(name = "PASSWORDAS400", length = 10)
	private String passwordAs400;
	
   @Transient
   @OneToMany 
   @ManyToManyRelationship(value=RelacionUsuarioRol.class,
   	exclusionCondition="NOT EXISTS(SELECT r.rol FROM RelacionUsuarioRol r WHERE r.rol = e AND r.usuario.id = ?)")
   @NewAction("") @RemoveAction("")
   @AddAction("ManyToMany.add")
   @RemoveSelectedAction("ManyToMany.removeSelected")
   @NoModify
   @Condition("EXISTS(SELECT r.rol FROM RelacionUsuarioRol r WHERE r.usuario.id = ${this.id} AND r.rol.id = ${id})")
   @ListProperties("nombre")
   private List<Rol> roles;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getPrimerApellido() {
		return primerApellido;
	}

	public void setPrimerApellido(String primerApellido) {
		this.primerApellido = primerApellido;
	}

	public String getSegundoApellido() {
		return segundoApellido;
	}

	public void setSegundoApellido(String segundoApellido) {
		this.segundoApellido = segundoApellido;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public boolean isSuperUsuario() {
		return superUsuario;
	}

	public void setSuperUsuario(boolean superUsuario) {
		this.superUsuario = superUsuario;
	}

	public boolean isCambiarPassword() {
		return cambiarPassword;
	}

	public void setCambiarPassword(boolean cambiarPassword) {
		this.cambiarPassword = cambiarPassword;
	}

	public String getUsuarioAs400() {
		return usuarioAs400;
	}

	public void setUsuarioAs400(String usuarioAs400) {
		this.usuarioAs400 = usuarioAs400;
	}

	public List<Rol> getRoles() {
		return roles;
	}

	public void setRoles(List<Rol> roles) {
		this.roles = roles;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPasswordAs400() {
		return passwordAs400;
	}

	public void setPasswordAs400(String passwordAs400) {
		this.passwordAs400 = passwordAs400;
	}
}
