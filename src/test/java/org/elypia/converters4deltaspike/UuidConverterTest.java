package org.elypia.converters4deltaspike;

import org.junit.jupiter.api.Test;

import java.net.*;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author seth@elypia.org (Seth Falco)
 * @since 1.0.0
 */
public class UuidConverterTest {

    @Test
    public void testConverteringPattern() {
        UuidConverter converter = new UuidConverter();

        final UUID expected = UUID.fromString("cebfb280-a7b0-4561-bc75-f71a1a08f66b");
        final UUID actual = converter.convert("cebfb280-a7b0-4561-bc75-f71a1a08f66b");

        assertEquals(expected, actual);
    }
}
