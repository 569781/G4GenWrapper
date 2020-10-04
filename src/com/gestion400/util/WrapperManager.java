package com.gestion400.util;

import java.io.*;
import java.util.*;

import com.gestion400.util.WrapperFactory.*;
import com.gswrapper.modelo.wrapper.*;

public class WrapperManager {
	
	private Map<String, Wrapper3270> wrappers;
	
	private static WrapperManager instance;
	
	public WrapperManager() {
		
		wrappers = new HashMap<>();
	}
	
	public static synchronized WrapperManager getInstance() {
		
		if(instance == null) instance = new WrapperManager();
		
		return instance;
	}
	
	public Wrapper3270 anyadirWrapper(String id, TipoWrapper tipoWrapper) throws IOException {
		
		Wrapper3270 wrapper = WrapperFactory.getWrapper(tipoWrapper);
		
		wrappers.put(id, wrapper);
		
		return wrapper;
	}
	
	public synchronized void borrarWrapper(String id) {
		
		if(wrappers.containsKey(id)) {
			
			Wrapper3270 wrapper = wrappers.get(id);
			
			wrappers.remove(id);
			
			cerrarSesion(wrapper);
		}
	}
	
	@SuppressWarnings("unchecked")
	public <W extends Wrapper3270> W getWrapper(Class<? extends Wrapper3270> W, String id) {
		
		W wrapper = null;
		
		if(wrappers.containsKey(id)) {
			
			wrapper = (W) wrappers.get(id);
		}
		
		return wrapper;
	}
	
	public boolean existeWrapper(String id) {
		
		return wrappers.containsKey(id);
	}
	
	private static final String KEY_MANDATO_CERRAR_SESION = "999";
	
	private void cerrarSesion(Wrapper3270 wrapper) {
		
		wrapper.escribir(KEY_MANDATO_CERRAR_SESION, true);
		
		wrapper.desconectar();
	}
}
