/*
 * Copyright 2020-2020 Elypia CIC and Contributors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.elypia.converters4deltaspike;

import org.apache.deltaspike.core.api.config.ConfigResolver;

import java.util.*;
import java.util.regex.*;

/**
 * Convert a configuration property value to a {@link UUID}.
 *
 * @author seth@elypia.org (Seth Falco)
 * @since 1.0.0
 */
public class UuidConverter implements ConfigResolver.Converter<UUID> {

    /**
     * @param value The value of the configuration property.
     * @return A {@link UUID} which represents the configuration property value.
     * @throws NullPointerException If the value is null.
     * @throws IllegalArgumentException If the value is not a valid {@link UUID}.
     */
    @Override
    public UUID convert(String value) {
        Objects.requireNonNull(value, "Value can't be null.");
        return UUID.fromString(value);
    }
}
