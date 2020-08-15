package org.elypia.converters4deltaspike;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * @author seth@elypia.org (Seth Falco)
 * @since 1.0.0
 */
public class UrlConverterTest {

    @Test
    public void testConverteringNormalUrl() throws MalformedURLException {
        UrlConverter converter = new UrlConverter();

        final URL expected = new URL("https://elypia.org/");
        final URL actual = converter.convert("https://elypia.org/");

        assertEquals(expected, actual);
    }

    @Test
    public void testFilteredProtocolPass() throws MalformedURLException {
        UrlConverter converter = new UrlConverter(List.of("http", "https"));

        final URL expected = new URL("https://gitlab.com/");
        final URL actual = converter.convert("https://gitlab.com/");

        assertEquals(expected, actual);
    }

    @Test
    public void testFilteredProtocolFail() {
        UrlConverter converter = new UrlConverter(List.of("https"));
        assertThrows(IllegalArgumentException.class, () -> converter.convert("http://elypia.org/"));
    }

    @ParameterizedTest
    @ValueSource(strings = {"Hello, world!", ":invalid.protocol"})
    public void testInvalidUrl(final String value) {
        UrlConverter converter = new UrlConverter();
        assertThrows(IllegalArgumentException.class, () -> converter.convert(value));
    }
}
