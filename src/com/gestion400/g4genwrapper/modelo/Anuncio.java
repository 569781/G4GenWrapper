package com.gestion400.g4genwrapper.modelo;


import java.sql.Timestamp;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;
import org.openxava.annotations.Hidden;
import org.openxava.annotations.Stereotype;
import com.gestion400.util.*;


@Table(schema = ConstantesAplicacion.ESQUEMA_APLICACION, name ="ANUNCIO")
@Entity
public class Anuncio {
	
	@PostPersist @PostUpdate @PostRemove
	private synchronized void onPostPersistUpdateRemove() {
		TablonDeAnuncios.getInstance().actualizarTablon();
	}

	@Id
	@Hidden
	@GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
	@Column(name="ID", length = 32)
	private String id;
	
	@Column(name = "ASUNTO", length = 200)
	private String asunto;
	
	@Stereotype("TEXT_AREA")
	@Column(name = "DESCRIPCIONBREVE", length = 500)
	private String descripcionBreve;

	@Stereotype("HTML_TEXT")
	@Column(name = "TEXTOANUNCIO", length = 3000)
	private String textoAnuncio;

	@Column(name = "FECHADEPUBLICACION")
	private Timestamp fechaDePublicacion;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAsunto() {
		return asunto;
	}

	public void setAsunto(String asunto) {
		this.asunto = asunto;
	}

	public String getDescripcionBreve() {
		return descripcionBreve;
	}

	public void setDescripcionBreve(String descripcionBreve) {
		this.descripcionBreve = descripcionBreve;
	}

	public String getTextoAnuncio() {
		return textoAnuncio;
	}

	public void setTextoAnuncio(String textoAnuncio) {
		this.textoAnuncio = textoAnuncio;
	}

	public Timestamp getFechaDePublicacion() {
		return fechaDePublicacion;
	}

	public void setFechaDePublicacion(Timestamp fechaDePublicacion) {
		this.fechaDePublicacion = fechaDePublicacion;
	}
	
	public String getFechaAnuncio() {
		
		return DateFormats.FORMATO_FECHA_ANUNCIO.format(fechaDePublicacion);
	}
}
