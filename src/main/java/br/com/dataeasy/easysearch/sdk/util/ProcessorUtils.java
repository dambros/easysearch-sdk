package br.com.dataeasy.easysearch.sdk.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ProcessorUtils {

    private static final String BIN_PATTERN = "@bin(%s)";
    private static final String DATE_PATTERN = "@date(%s,%s)";
    private static final String REF_PATTERN = "@ref(%s)";

    public static String getBinProcessor(String value) {
        return String.format(BIN_PATTERN, value);
    }

    public static String getDateProcessor(String pattern, Date date) {
        String dateStr = new SimpleDateFormat(pattern).format(date);
        return String.format(DATE_PATTERN, pattern, dateStr);
    }

    public static String getRefProcessor(String name) {
        return String.format(REF_PATTERN, name);
    }

}
