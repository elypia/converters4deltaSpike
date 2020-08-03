package org.elypia.converters4deltaspike;

import org.apache.deltaspike.core.api.config.ConfigResolver;

import java.util.*;

/**
 * Converts a localization pattern into a Java {@link Locale} object.
 *
 * @author seth@elypia.org (Seth Falco)
 * @since 1.0.0
 */
public class LocaleConverter implements ConfigResolver.Converter<Locale> {

    /**
     * @param value The value of the configuration property.
     * @return A {@link Locale} which represents the configuration property value.
     * @throws NullPointerException If the value is null.
     */
    @Override
    public Locale convert(String value) {
        Objects.requireNonNull(value, "Value can't be null.");
        return Locale.forLanguageTag(value);
    }
}
