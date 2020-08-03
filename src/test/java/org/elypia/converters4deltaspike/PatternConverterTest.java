package org.elypia.converters4deltaspike;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PatternConverterTest {

    @Test
    public void testConverteringPattern() {
        PatternConverter converter = new PatternConverter();

        final String expected = "(?i)Ow.+O";
        final String actual = converter.convert("(?i)Ow.+O").toString();

        assertEquals(expected, actual);
    }
}
