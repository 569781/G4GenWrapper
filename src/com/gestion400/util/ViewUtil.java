package com.gestion400.util;

import org.openxava.view.*;

import com.gswrapper.util.*;

public enum ViewUtil {
	
	INSTANCE;
	
	public void setValue(View view, String propiedad, Object valor) {
		
		if(!Condition.empty(valor)) {
			
			view.setValue(propiedad, valor);
		}
	}

}
