package br.com.dataeasy.easysearch.sdk.util;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class StringUtilsTest {

    @Test
    public void testIsNullOrEmpty() {
        assertFalse(StringUtils.isNullOrEmpty("abcd"));
        assertTrue(StringUtils.isNullOrEmpty(""));
        assertTrue(StringUtils.isNullOrEmpty(null));
    }

    @Test
    public void testUpperCase() {
        String emptyStr = "";
        assertEquals(emptyStr, StringUtils.upperCase(emptyStr));
        assertEquals(null, StringUtils.upperCase(null));

        String nonEmptyStr = "dataEasy";
        assertEquals(nonEmptyStr.toUpperCase(), StringUtils.upperCase(nonEmptyStr));
    }
}
