package org.elypia.converters4deltaspike;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.DayOfWeek;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * @author seth@elypia.org (Seth Falco)
 * @since 1.0.0
 */
public class EnumConverterTest {

    @Test
    public void testConvertTimeUnit() {
        EnumConverter converter = new EnumConverter();

        final TimeUnit expected = TimeUnit.NANOSECONDS;
        final Enum actual = converter.convert("java.util.concurrent.TimeUnit.NANOSECONDS");

        assertEquals(expected, actual);
    }

    @Test
    public void testConvertDayOfWeek() {
        EnumConverter converter = new EnumConverter();

        final DayOfWeek expected = DayOfWeek.MONDAY;
        final Enum actual = converter.convert("java.time.DayOfWeek#MONDAY");

        assertEquals(expected, actual);
    }

    /**
     * When the {@link EnumConverter} is constructed with a type,
     * it's permissible to only pass the {@link Enum} constant
     * name without the package or class name.
     */
    @Test
    public void testEnumNameOnlyFromTypedInstance() {
        EnumConverter converter = new EnumConverter(DayOfWeek.class);

        final DayOfWeek expected = DayOfWeek.MONDAY;
        final Enum actual = converter.convert("MONDAY");

        assertEquals(expected, actual);
    }

    @Test
    public void testConvertWroteEnumType() {
        EnumConverter converter = new EnumConverter(TimeUnit.class);
        assertThrows(IllegalArgumentException.class, () -> converter.convert("java.time.DayOfWeek#MONDAY"));
    }

    @ParameterizedTest
    @ValueSource(strings = {"JAVA.TIME.DAYOFWEEK#MONDAY", "JAVA-TIME-DAYOFWEEK#MONDAY"})
    public void testBrokenNamingConvention(final String value) {
        EnumConverter converter = new EnumConverter();
        assertThrows(IllegalArgumentException.class, () -> converter.convert(value));
    }

    @ParameterizedTest
    @ValueSource(strings = {"java.lang.Exception#MONDAY", "java.lang.String#MONDAY"})
    public void testNonEnumClasses(final String value) {
        EnumConverter converter = new EnumConverter();
        assertThrows(IllegalArgumentException.class, () -> converter.convert(value));
    }

    @ParameterizedTest
    @ValueSource(strings = {"class.does.not.exist#MONDAY", "java.lang.does.not.exist#MONDAY"})
    public void testNonExistingClasses(final String value) {
        EnumConverter converter = new EnumConverter();
        assertThrows(IllegalArgumentException.class, () -> converter.convert(value));
    }
}
