/*
 * Copyright 2020-2020 Elypia CIC
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

import java.util.Objects;
import java.util.regex.*;

/**
 * Converts a regular expression into a Java {@link Pattern} object.
 *
 * @author seth@elypia.org (Seth Falco)
 * @since 1.0.0
 */
public class PatternConverter implements ConfigResolver.Converter<Pattern> {

    /**
     * @param value The value of the configuration property.
     * @return A {@link Pattern} which represents the compiled configuration property.
     * @throws NullPointerException If the value is null.
     * @throws PatternSyntaxException If the regular expression {@link String} provided is malformed.
     */
    @Override
    public Pattern convert(String value) {
        Objects.requireNonNull(value, "Regular expression can't be null.");
        return Pattern.compile(value);
    }
}
