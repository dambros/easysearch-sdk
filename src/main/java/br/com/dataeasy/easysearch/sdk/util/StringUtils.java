package br.com.dataeasy.easysearch.sdk.util;

public class StringUtils {

    /**
     * @return true if the given value is either null or the empty string
     */
    public static boolean isNullOrEmpty(String value) {
        return value == null || value.isEmpty();
    }

    /**
     * Converts a given String to upper case
     *
     * @param str the string to be converted to upper case
     * @return the upper case of string, or itself if string is null/empty
     */
    public static String upperCase(String str) {
        if (isNullOrEmpty(str)) {
            return str;
        }
        return str.toUpperCase();
    }
}
