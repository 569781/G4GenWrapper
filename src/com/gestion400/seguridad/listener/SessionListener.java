package com.gestion400.seguridad.listener;

import javax.servlet.annotation.*;
import javax.servlet.http.*;

import com.gestion400.util.*;

@WebListener
public class SessionListener implements HttpSessionListener{

	@Override
	public void sessionCreated(HttpSessionEvent event) {}

	@Override
	public void sessionDestroyed(HttpSessionEvent event) {
		
		HttpSession sesion = event.getSession();
		
		WrapperManager.getInstance().borrarWrapper(sesion.getId());
	}

}
