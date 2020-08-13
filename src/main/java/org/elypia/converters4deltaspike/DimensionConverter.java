package org.elypia.converters4deltaspike;

import org.apache.deltaspike.core.api.config.ConfigResolver;

import java.awt.*;
import java.io.File;
import java.util.Objects;
import java.util.regex.*;

/**
 * <p>
 *     Allows converting configuration values to a {@link Dimension}.
 *     This can be used to load settings for default dimensions of {@link Component}s
 *     {@link Window}s, or {@link Shape}s.
 * </p>
 *
 * <p>
 *     Accepts a single {@link Integer} value, or two {@link Integer} values
 *     seperated by the character <code>x</code>.
 * </p>
 *
 * <p>The dimensions must not be negative, and must be {@link Integer} values.</p>
 *
 * @author seth@elypia.org (Seth Falco)
 * @since 1.1.0
 */
public class DimensionConverter implements ConfigResolver.Converter<Dimension> {

    /** Regular expression used to validate and tokenizer the {@link String}.  */
    private static final Pattern DIMENSION_PATTERN = Pattern.compile("(?<x>\\d+)(?:x(?<y>\\d+))?");

    /**
     * @param value The value of the configuration property.
     * @return A {@link File} which represents the configuration property value.
     * @throws NullPointerException If the value is null.
     * @throws NumberFormatException If the {@link Dimension} width or height is bigger than {@link Integer#MAX_VALUE}.
     */
    @Override
    public Dimension convert(final String value) {
        Objects.requireNonNull(value, "Dimensions can not be null.");

        if (value.isEmpty())
            throw new IllegalArgumentException("Dimensions can not be empty.");

        Matcher matcher = DIMENSION_PATTERN.matcher(value);

        if (!matcher.matches())
            throw new IllegalArgumentException("Dimension doesn't match format: {width/height} or {width}x{height}");

        String x = matcher.group(1);
        String y = matcher.group(2);

        int xValue = Integer.parseInt(x);
        int yValue = (y != null) ? Integer.parseInt(y) : xValue;

        return new Dimension(xValue, yValue);
    }
}
