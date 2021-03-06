package com.eldon.util;

import java.util.Date;
import java.util.Locale;
import java.io.InputStream;
import java.sql.Blob;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.apache.commons.io.IOUtils;

public class Util {	
	Locale locale = new Locale("es","MX");
	public String FormateDate(String fecha){
		String fecFormat;
		SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
		try {
			date = dt.parse(fecha);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
        SimpleDateFormat dt1 = new SimpleDateFormat("EEE, d MMM",locale);
        
        fecFormat = dt1.format(date);
        return fecFormat;
	}
	
	public String FormateHora(String hora){
		String horaFormat;
		SimpleDateFormat dt = new SimpleDateFormat("HH:mm:ss");
        Date date = null;
		try {
			date = dt.parse(hora);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
        SimpleDateFormat dt1 = new SimpleDateFormat("HH:mm");
        
        horaFormat = dt1.format(date);
        return horaFormat;
	}
	
	public Blob convierteImagen(InputStream imagen) throws Exception{
		Blob blob = null;
		byte[] bytes = IOUtils.toByteArray(imagen);
		blob = new javax.sql.rowset.serial.SerialBlob(bytes);
		return blob;
		
	}
}
