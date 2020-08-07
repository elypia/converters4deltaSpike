package org.elypia.converters4deltaspike;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.net.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author seth@elypia.org (Seth Falco)
 * @since 1.1.0
 */
public class UriConverterTest {

    @Test
    public void testConverteringUri() throws URISyntaxException {
        UriConverter converter = new UriConverter();

        final URI expected = new URI("mailto:java-net@java.sun.com");
        final URI actual = converter.convert("mailto:java-net@java.sun.com");

        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @ValueSource(strings = {"mailto:java-net@java.sun.com", "news:comp.lang.java", "urn:isbn:096139210x", "steam://connect/192.0.2.1:27015"})
    public void testUriNoError(final String value) {
        UriConverter converter = new UriConverter();
        assertDoesNotThrow(() -> converter.convert(value));
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "Hello, world!"})
    public void testUriErrors(final String value) {
        UriConverter converter = new UriConverter();
        assertThrows(IllegalArgumentException.class, () -> converter.convert(value));
    }
}
