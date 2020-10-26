package com.gestion400.seguridad.listener;

import javax.servlet.annotation.*;
import javax.servlet.http.*;

import org.apache.log4j.Logger;

import com.gestion400.util.*;

@WebListener
public class SessionListener implements HttpSessionListener{
	
	private static final Logger log = Logger.getLogger(SessionListener.class);

	@Override
	public void sessionCreated(HttpSessionEvent event) {
		
		log.info("Iniciando la sesión la sesión " + event.getSession().getId());
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent event) {
		
		HttpSession sesion = event.getSession();
		
		log.info("Cerrando sesión la sesión " + sesion.getId());
		
		WrapperManager.getInstance().borrarWrapper(sesion.getId());
	}
}
