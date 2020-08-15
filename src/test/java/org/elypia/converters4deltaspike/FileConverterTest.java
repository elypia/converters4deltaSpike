package org.elypia.converters4deltaspike;

import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * @author seth@elypia.org (Seth Falco)
 * @since 1.1.0
 */
public class FileConverterTest {

    @Test
    public void testConverteringFile() {
        final FileConverter converter = new FileConverter();

        final File expected = new File("/");
        final File actual = converter.convert("/");

        assertEquals(expected, actual);
    }

    @Test
    public void testConvertingNull() {
        final FileConverter converter = new FileConverter();
        assertThrows(NullPointerException.class, () -> converter.convert(null));
    }

    @Test
    public void testConvertingEmpty() {
        final FileConverter converter = new FileConverter();
        assertThrows(IllegalArgumentException.class, () -> converter.convert(""));
    }
}
