package com.db.oracle.estudos.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DataUtils {

	private static final String DEFAULT_DATE_FORMAT = "dd/MM/yyyy";
	private static final String OTHER_DATE_FORMAT = "yyyy-MM-dd";

	public static Date stringToDate(String dateStr) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat(DEFAULT_DATE_FORMAT);
		return sdf.parse(dateStr);
	}
	
	public static Date stringToDateOtherFormat(String dateStr) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat(OTHER_DATE_FORMAT);
		return sdf.parse(dateStr);
	}
	
	public static Date ajustarHorario(Date data) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(data);
        
        if (cal.get(Calendar.HOUR_OF_DAY) == 21 && cal.get(Calendar.MINUTE) == 0) {
            cal.add(Calendar.HOUR_OF_DAY, 3);
        }
        
        return cal.getTime();
    }
	
	public static Integer calculoIdade(Date dataNascimento) {
	    Calendar dataNasc = Calendar.getInstance();
	    dataNasc.setTime(dataNascimento);
	    
	    Calendar dataAtual = Calendar.getInstance();
	    dataAtual.setTime(new Date());
	    
	    Integer idade = dataAtual.get(Calendar.YEAR) - dataNasc.get(Calendar.YEAR);
	    
	    if (dataAtual.get(Calendar.DAY_OF_YEAR) < dataNasc.get(Calendar.DAY_OF_YEAR)) {
	        idade--;
	    }
	    
	    return idade;
	}


}
