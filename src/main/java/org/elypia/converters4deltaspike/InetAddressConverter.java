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

import java.net.*;
import java.util.Objects;
import java.util.regex.Pattern;

/**
 * For converting configuration property values to an IP address.
 *
 * @author seth@elypia.org (Seth Falco)
 * @since 1.0.0
 * @see <a href="https://en.wikipedia.org/wiki/Inet_address">IP Address on Wikipedia</a>
 */
public class InetAddressConverter implements ConfigResolver.Converter<InetAddress> {

    /**
     * @param value The value of the configuration property.
     * @return A {@link Pattern} which represents the compiled configuration property.
     * @throws NullPointerException If the value is null.
     * @throws IllegalArgumentException If a host name was specified and the IP address couldn't be obtained.
     */
    @Override
    public InetAddress convert(String value) {
        Objects.requireNonNull(value, "Value can't be null.");

        try {
            return InetAddress.getByName(value);
        } catch (UnknownHostException ex) {
            throw new IllegalArgumentException("Unable to get IP address of the named host.", ex);
        }
    }
}
