package com.db.oracle.estudos.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DataUtils {

	private static final String DEFAULT_DATE_FORMAT = "dd/MM/yyyy";
	private static final String OTHER_DATE_FORMAT = "yyyy-MM-dd";
	private static final String STRING_DATE = "stringToDate()";
	private static final String STRING_DATE_OTHER_FORMAT = "stringToDateOtherFormat()";
	private static final String AJUSTAR_HORARIO = "ajustarHorario()";
	private static final String CALCULO_IDADE = "calculoIdade()";
	
	private static final Logger LOG = LoggerFactory.getLogger(DataUtils.class);

	public static Date stringToDate(String dateStr) throws ParseException {
		LOG.info("START - " + STRING_DATE + " - " + new Date());
		SimpleDateFormat sdf = new SimpleDateFormat(DEFAULT_DATE_FORMAT);
		Date response = sdf.parse(dateStr);
		LOG.info("FINISH - " + STRING_DATE + " - " + new Date() + " " + response);
		
		return response;
	}
	
	public static Date stringToDateOtherFormat(String dateStr) throws ParseException {
		LOG.info("START - " + STRING_DATE_OTHER_FORMAT + " - " + new Date());
		SimpleDateFormat sdf = new SimpleDateFormat(OTHER_DATE_FORMAT);
		Date response = sdf.parse(dateStr);
		LOG.info("FINISH - " + STRING_DATE_OTHER_FORMAT + " - " + new Date() + " " + response);

		return response;
	}
	
	public static Integer calculoIdade(Date dataNascimento) {
		LOG.info("START - " + CALCULO_IDADE + " - " + new Date());
	    Calendar dataNasc = Calendar.getInstance();
	    dataNasc.setTime(dataNascimento);
	    
	    Calendar dataAtual = Calendar.getInstance();
	    dataAtual.setTime(new Date());
	    
	    Integer idade = dataAtual.get(Calendar.YEAR) - dataNasc.get(Calendar.YEAR);
	    
	    if (dataAtual.get(Calendar.DAY_OF_YEAR) < dataNasc.get(Calendar.DAY_OF_YEAR)) {
	        idade--;
	    }
	    
	    LOG.info("FINISH - " + CALCULO_IDADE + " - " + new Date() + " " + idade);
	    return idade;
	}


}
