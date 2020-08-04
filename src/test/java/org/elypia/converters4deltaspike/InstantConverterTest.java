package org.elypia.converters4deltaspike;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.Instant;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author seth@elypia.org (Seth Falco)
 * @since 1.0.0
 */
public class InstantConverterTest {

    @Test
    public void testConverteringMilliseconds() {
        InstantConverter converter = new InstantConverter();

        final Instant expected = Instant.ofEpochMilli(1596500083605L);
        final Instant actual = converter.convert("1596500083605");

        assertEquals(expected, actual);
    }

    @Test
    public void testConverteringInstantString() {
        InstantConverter converter = new InstantConverter();

        final Instant expected = Instant.ofEpochMilli(1196676930000L);
        final Instant actual = converter.convert("2007-12-03T10:15:30.00Z");

        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @ValueSource(strings = {"Hello, world!", "two-thousand-and-eighteen", "200,000,000,000"})
    public void testConverteringInvalidStrings(final String value) {
        InstantConverter converter = new InstantConverter();
        assertThrows(IllegalArgumentException.class, () -> converter.convert(value));
    }
}
