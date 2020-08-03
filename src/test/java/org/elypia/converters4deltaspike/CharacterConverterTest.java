package org.elypia.converters4deltaspike;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

public class CharacterConverterTest {

    @Test
    public void testConvertingSingleCharacterString() {
        CharacterConverter converter = new CharacterConverter();

        final char expected = 'a';
        final char actual = converter.convert("a");

        assertEquals(expected, actual);
    }

    @Test
    public void testConvertingSingleCharacterSpace() {
        CharacterConverter converter = new CharacterConverter();

        final char expected = ' ';
        final char actual = converter.convert(" ");

        assertEquals(expected, actual);
    }

    @Test
    public void testConvertingSingleNumber() {
        CharacterConverter converter = new CharacterConverter();

        final char expected = '1';
        final char actual = converter.convert("1");

        assertEquals(expected, actual);
    }

    @Test
    public void testConvertSymbol() {
        CharacterConverter converter = new CharacterConverter();

        final char expected = '$';
        final char actual = converter.convert("$");

        assertEquals(expected, actual);
    }

    /**
     * If a hexadecimal value is provided, we'll convert it to a
     * a character if possible.
     */
    @Test
    public void testConvertHex() {
        CharacterConverter converter = new CharacterConverter();

        final char expected = 'A';
        final char actual = converter.convert("0x41");

        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @ValueSource(strings = {"Hello, world!", "  ", "AA", "11", "00", "xx"})
    public void testInvalidValue(final String value) {
        CharacterConverter converter = new CharacterConverter();
        assertThrows(IllegalArgumentException.class, () -> converter.convert(value));
    }

    @Test
    public void testEmptyString() {
        CharacterConverter converter = new CharacterConverter();
        assertThrows(IllegalArgumentException.class, () -> converter.convert(""));
    }
}
