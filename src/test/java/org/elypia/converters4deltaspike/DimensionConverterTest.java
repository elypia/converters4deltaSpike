package org.elypia.converters4deltaspike;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author seth@elypia.org (Seth Falco)
 * @since 1.1.0
 */
public class DimensionConverterTest {

    @Test
    public void testConverteringDimension() {
        final DimensionConverter converter = new DimensionConverter();

        final Dimension expected = new Dimension(1920, 1080);
        final Dimension actual = converter.convert("1920x1080");

        assertEquals(expected, actual);
    }

    @Test
    public void testConverteringSquare() {
        final DimensionConverter converter = new DimensionConverter();

        final Dimension expected = new Dimension(512, 512);
        final Dimension actual = converter.convert("512");

        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @ValueSource(strings = {"Hello, world!", "<3", "512n512", "0xFF", "", "-512x512", "-512"})
    public void testInvalidDimensions(final String value) {
        final DimensionConverter converter = new DimensionConverter();
        assertThrows(IllegalArgumentException.class, () -> converter.convert(value));
    }

    @ParameterizedTest
    @ValueSource(strings = {"3000000000", "100x3000000000", "3000000000x100", "3000000000x3000000000"})
    public void testInvalidNumberFormatException(final String value) {
        final DimensionConverter converter = new DimensionConverter();
        assertThrows(NumberFormatException.class, () -> converter.convert(value));
    }
}
