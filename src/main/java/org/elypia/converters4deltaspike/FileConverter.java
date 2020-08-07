package org.elypia.converters4deltaspike;

import org.apache.deltaspike.core.api.config.ConfigResolver;

import java.io.File;
import java.util.Objects;

/**
 * @author seth@elypia.org (Seth Falco)
 * @since 1.1.0
 */
public class FileConverter implements ConfigResolver.Converter<File> {

    /**
     * @param value The value of the configuration property.
     * @return A {@link File} which represents the configuration property value.
     * @throws NullPointerException If the value is null.
     */
    @Override
    public File convert(String value) {
        Objects.requireNonNull(value, "File name can not be null.");

        if (value.isEmpty())
            throw new IllegalArgumentException("File name can not be empty.");

        return new File(value);
    }
}
