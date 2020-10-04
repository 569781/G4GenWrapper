package com.gestion400.util;

import java.util.*;

import org.openxava.util.*;

import com.gestion400.dao.*;
import com.gestion400.g4genwrapper.modelo.*;

public class TablonDeAnuncios {
	
	private static TablonDeAnuncios instance;
	
	private String tituloTablon;
	
	private String ayuntamiento;
	
	private List<Anuncio> anuncios;
	
	private boolean actualizarAnuncios = false;
	
	
	public synchronized static TablonDeAnuncios getInstance() {
		 
		if (instance ==null) {
			instance = new TablonDeAnuncios();
		}
		
		return instance;
	}
	
	
	public TablonDeAnuncios() {
		anuncios = AnuncioDao.INSTANCE.findAnuncios();
	}
	
	
	public synchronized List<Anuncio> getAnuncios() {
		
		if(actualizarAnuncios) {
			anuncios = AnuncioDao.INSTANCE.findAnuncios();
			actualizarAnuncios = false;
		}
		
		return anuncios;
		
	}
	
	
	public synchronized void actualizarTablon() {
		actualizarAnuncios = true;
	}


	public synchronized String getTituloTablon() {
		
		if(Is.empty(tituloTablon)) {
			tituloTablon = "Tablón de anuncios";
		}
		
		return tituloTablon;
	}
	
	
	public synchronized String getAyuntamiento() {
		
		if(Is.empty(ayuntamiento)) {
			ayuntamiento = ConstantesAplicacion.ENTIDAD;
		}
		
		return ayuntamiento;
		
	}
}
