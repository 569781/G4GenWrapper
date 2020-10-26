package com.gestion400.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public enum DateFormats {
	
	FORMATO_FECHA_ANUNCIO(new SimpleDateFormat("dd/MM/yyyy HH:mm")),
	
	FORMATO_FECHA_AS400(new SimpleDateFormat("dd.MM.yyyy"));

	private DateFormat format;
	
	private DateFormats(DateFormat format) {
		
		this.format = format;
	}
	
	public String format(Date date) {
		
		return format.format(date);
	}
	
	public Date parse(String date) throws ParseException {
		
		return format.parse(date);
	}
}
