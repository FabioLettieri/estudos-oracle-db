package com.db.oracle.estudos.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.junit.jupiter.api.Test;

public class DataUtilsTest {
	
	private static final String OTHER_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
	private static final String DEFAULT_DATE_FORMAT = "dd/MM/yyyy";

	@Test
	public void testStringToDate() throws ParseException {
		String dataString = "2024-06-15";
		Date dataEsperada = new Date(124, 5, 15); // 15 de junho de 2024
		Date dataConvertida = DataUtils.stringToDateOtherFormat(dataString);
		assertEquals(dataEsperada, dataConvertida);
	}

    @Test
    public void testStringToDateOtherFormat() throws ParseException {
        String dataString = "2024-06-15";
        Date dataEsperada = new SimpleDateFormat(OTHER_DATE_FORMAT.split(" ")[0]).parse(dataString);
        Date dataConvertida = DataUtils.stringToDateOtherFormat(dataString);
        assertEquals(dataEsperada, dataConvertida);
    }
    
    @Test
    public void testStringToDateValida() throws ParseException {
        String dataString = "15/06/2024";
        Date dataEsperada = new SimpleDateFormat(DEFAULT_DATE_FORMAT).parse(dataString);
        Date dataConvertida = DataUtils.stringToDate(dataString);
        assertEquals(dataEsperada, dataConvertida);
    }
    
    @Test
    public void testAjustarHorario21Horas() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(OTHER_DATE_FORMAT);
        String dataString = "2024-06-15 21:00:00";
        Date dataOriginal = sdf.parse(dataString);

        Calendar cal = Calendar.getInstance();
        cal.setTime(dataOriginal);
        cal.add(Calendar.HOUR_OF_DAY, 3);
        Date dataEsperada = cal.getTime();

        Date dataAjustada = DataUtils.ajustarHorario(dataOriginal);

        assertEquals(dataEsperada, dataAjustada); // A data ajustada deve ter 3 horas adicionadas
    }

    @Test
    public void testAjustarHorarioDiferenteDe21Horas() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(OTHER_DATE_FORMAT);
        String dataString = "2024-06-15 20:00:00"; // Horário diferente de 21:00
        Date dataOriginal = sdf.parse(dataString);

        Date dataAjustada = DataUtils.ajustarHorario(dataOriginal);

        assertEquals(dataOriginal, dataAjustada); // A data ajustada deve ser igual à original
    }
    
    @Test
    public void testAjustarHorarioComMinutos() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(OTHER_DATE_FORMAT);
        String dataString = "2024-06-15 21:30:00"; // Horário com minutos diferentes de 00
        Date dataOriginal = sdf.parse(dataString);

        Date dataAjustada = DataUtils.ajustarHorario(dataOriginal);

        assertEquals(dataOriginal, dataAjustada); // A data ajustada deve ser igual à original
    }
    
    @Test
    public void testAjustarHorario21HorasZeroMinutos() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(OTHER_DATE_FORMAT);
        String dataString = "2024-06-15 21:00:00"; // Horário às 21:00
        Date dataOriginal = sdf.parse(dataString);

        Calendar cal = Calendar.getInstance();
        cal.setTime(dataOriginal);
        cal.add(Calendar.HOUR_OF_DAY, 3);
        Date dataEsperada = cal.getTime();

        Date dataAjustada = DataUtils.ajustarHorario(dataOriginal);

        assertEquals(dataEsperada, dataAjustada); // A data ajustada deve ter 3 horas adicionadas
    }

    @Test
    public void testAjustarHorarioZeroHoras() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(OTHER_DATE_FORMAT);
        String dataString = "2024-06-15 00:00:00"; // Horário às 00:00
        Date dataOriginal = sdf.parse(dataString);

        Date dataAjustada = DataUtils.ajustarHorario(dataOriginal);

        assertEquals(dataOriginal, dataAjustada); // A data ajustada deve ser igual à original
    }
    
    @Test
    public void testCalculoIdadeAniversarioAntesDeHoje() throws ParseException {
        String dataString = "1994-06-14";
        
        Integer idadeEsperada = 30; // Considerando a data atual é 2024-06-15
        Integer idadeCalculada = DataUtils.calculoIdade(DataUtils.stringToDateOtherFormat(dataString));
        
        assertEquals(idadeEsperada, idadeCalculada);
    }

    @Test
    public void testCalculoIdadeAniversarioHoje() throws ParseException {
        String dataString = "1994-06-15";
        
        Integer idadeEsperada = 30; // Considerando a data atual é 2024-06-15
        Integer idadeCalculada = DataUtils.calculoIdade(DataUtils.stringToDateOtherFormat(dataString));
        
        assertEquals(idadeEsperada, idadeCalculada);
    }

    @Test
    public void testCalculoIdadeAniversarioDepoisDeHoje() throws ParseException {
        String dataString = "1994-06-16";
        
        Integer idadeEsperada = 29; // Considerando a data atual é 2024-06-15
        Integer idadeCalculada = DataUtils.calculoIdade(DataUtils.stringToDateOtherFormat(dataString));
        
        assertNotEquals(idadeEsperada, idadeCalculada);
    }

    @Test
    public void testCalculoIdadeDiferenteAno() throws ParseException {
        String dataString = "1993-12-31";
        
        Integer idadeEsperada = 30; // Considerando a data atual é 2024-06-15
        Integer idadeCalculada = DataUtils.calculoIdade(DataUtils.stringToDateOtherFormat(dataString));
        
        assertEquals(idadeEsperada, idadeCalculada);
    }
}
