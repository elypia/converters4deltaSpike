package org.elypia.converters4deltaspike;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

public class ColorConverterTest {

    @Test
    public void testConverteringPattern() {
        ColorConverter converter = new ColorConverter();

        final Color expected = Color.BLACK;
        final Color actual = converter.convert("#000000");

        assertEquals(expected, actual);
    }

    @Test
    public void testConverteringPatternWithAlpha() {
        ColorConverter converter = new ColorConverter();

        final Color expected = Color.LIGHT_GRAY;
        final Color actual = converter.convert("#C0C0C0FF");

        assertEquals(expected, actual);
    }

    @Test
    public void testConverteringPattern3Digit() {
        ColorConverter converter = new ColorConverter();

        final Color expected = Color.WHITE;
        final Color actual = converter.convert("#FFF");

        assertEquals(expected, actual);
    }

    @Test
    public void testConverteringPattern4Digit() {
        ColorConverter converter = new ColorConverter();

        final Color expected = Color.YELLOW;
        final Color actual = converter.convert("#FF0F");

        assertEquals(expected, actual);
    }

    @Test
    public void testConverteringLiteralHex() {
        ColorConverter converter = new ColorConverter();

        final Color expected = Color.BLUE;
        final Color actual = converter.convert("0x0000FF");

        assertEquals(expected, actual);
    }

    @Test
    public void testConverteringColorName() {
        ColorConverter converter = new ColorConverter();

        final Color expected = Color.WHITE;
        final Color actual = converter.convert("white");

        assertEquals(expected, actual);
    }

    @Test
    public void testConverteringColorNameCaps() {
        ColorConverter converter = new ColorConverter();

        final Color expected = Color.LIGHT_GRAY;
        final Color actual = converter.convert("LIGHTGRAY");

        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @ValueSource(strings = {"white", "lightgray", "gray", "darkgray", "black", "red", "pink", "orange", "yellow", "green", "magenta", "cyan", "blue"})
    public void testAllLiteralColors(final String value) {
        ColorConverter converter = new ColorConverter();
        assertDoesNotThrow(() -> converter.convert(value));
    }

    @ParameterizedTest
    @ValueSource(strings = {"#FFZ", "#FFFY", "#FFFFFX", "#FFFFFFFW"})
    public void testConverteringInvalidHexNumbers(final String value) {
        ColorConverter converter = new ColorConverter();
        assertThrows(NumberFormatException.class, () -> converter.convert(value));
    }

    @ParameterizedTest
    @ValueSource(strings = {"#", "#F", "#FF", "#FFFFF", "#FFFFFFF", "#FFFFFFFFF"})
    public void testConverteringInvalidHexLength(final String value) {
        ColorConverter converter = new ColorConverter();
        assertThrows(IllegalArgumentException.class, () -> converter.convert(value));
    }
}
