package br.com.dataeasy.easysearch.sdk.util;

import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.assertEquals;

public class ProcessorUtilsTest {

    @Test
    public void testBinProcessor() {
        String ticket = "abcde12345";
        String expectedReturn = String.format("@bin(%s)", ticket);
        String result = ProcessorUtils.getBinProcessor(ticket);
        assertEquals(expectedReturn, result);
    }

    @Test
    public void testRefProcessor() {
        String name = "fieldValue";
        String expectedReturn = String.format("@ref(%s)", name);
        String result = ProcessorUtils.getRefProcessor(name);
        assertEquals(expectedReturn, result);
    }

    @Test
    public void testDateProcessor() throws ParseException {
        String pattern = "dd/MM/yyyy HH:mm:ss";
        String dateStr = "19/08/1988 15:20:50";
        Date date = new SimpleDateFormat(pattern).parse(dateStr);

        String expectedReturn = String.format("@date(%s,%s)", pattern, dateStr);
        String result = ProcessorUtils.getDateProcessor(pattern, date);
        assertEquals(expectedReturn, result);
    }
}
