package org.elypia.converters4deltaspike;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.*;
import java.time.temporal.ChronoUnit;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author seth@elypia.org (Seth Falco)
 * @since 1.1.0
 */
public class PeriodConverterTest {

    @Test
    public void testConverteringDefault() {
        PeriodConverter converter = new PeriodConverter();

        final Period expected = Period.ofDays(2);
        final Period actual = converter.convert("2");

        assertEquals(expected, actual);
    }

    @Test
    public void testConverteringDurationString() {
        PeriodConverter converter = new PeriodConverter();

        final Period expected = Period.parse("P1Y2M3D");
        final Period actual = converter.convert("P1Y2M3D");

        assertEquals(expected, actual);
    }

    /**
     * As this is meant to be for technical usage, such as developers, or administrators
     * of software, we don't allow localized {@link Number}s such as <code>1,000</code>. Only programatically
     * correct values like <code>1000</code>.
     *
     * @param value Test value.
     */
    @ParameterizedTest
    @ValueSource(strings = {"1,000", "Hello, world!", "100.000.000", "Z"})
    public void testConverteringInvalidNumbers(final String value) {
        PeriodConverter converter = new PeriodConverter();
        assertThrows(IllegalArgumentException.class, () -> converter.convert(value));
    }
}
