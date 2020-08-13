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

import java.time.Period;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

/**
 * <p>Converts a period of time to the {@link Period} type.</p>
 *
 * Accepts the following:
 * <ul>
 *     <li>{@link Period} {@link String} format, for example <code>P2Y</code> (2 {@link ChronoUnit#YEARS}).</li>
 *     <li>{@link Integer} number of {@link ChronoUnit#DAYS}, for example <code>31</code> (31 {@link ChronoUnit#DAYS}).</li>
 * </ul>
 *
 * @author seth@elypia.org (Seth Falco)
 * @since 1.1.0
 */
public class PeriodConverter implements ConfigResolver.Converter<Period> {

    /**
     * @param value The value of the configuration property.
     * @return A value provided, converting to a {@link Period} of time instead.
     * @throws NullPointerException If the value is null.
     * @throws IllegalArgumentException If the value is not a valid {@link Period} {@link String},
     * and isn't a valid {@link Integer} to represent {@link ChronoUnit#DAYS}.
     */
    @Override
    public Period convert(String value) {
        Objects.requireNonNull(value, "Value can't be null.");

        try {
            return Period.parse(value);
        } catch (DateTimeParseException ex) {
            try {
                int number = Integer.parseInt(value);
                return Period.ofDays(number);
            } catch (RuntimeException rEx) {
                throw new IllegalArgumentException("Value provided is not a valid Period String, or valid Integer value and can't be converted to a Period.", rEx);
            }
        }
    }
}
