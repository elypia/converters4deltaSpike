package org.elypia.converters4deltaspike;

import org.junit.jupiter.api.Test;

import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author seth@elypia.org (Seth Falco)
 * @since 1.0.0
 */
public class LocaleConverterTest {

    @Test
    public void testConvertStandardLocale() {
        LocaleConverter converter = new LocaleConverter();

        final Locale expected = Locale.ENGLISH;
        final Locale actual = converter.convert("en");

        assertEquals(expected, actual);
    }

    @Test
    public void testConvertCustomLocale() {
        LocaleConverter converter = new LocaleConverter();

        final Locale expected = Locale.forLanguageTag("en-owo");
        final Locale actual = converter.convert("en-owo");

        assertEquals(expected, actual);
    }
}
