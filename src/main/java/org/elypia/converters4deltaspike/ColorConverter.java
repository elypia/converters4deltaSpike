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
import org.slf4j.*;

import java.awt.*;
import java.util.Objects;

/**
 * <p>Converts a configuration property into a Java {@link Color} object.</p>
 *
 * <p>
 *     This converter aims to be compatible with some some of the web color
 *     formats supported by browsers with CSS, rather than only support
 *     literal intepretations of numbers, such as:
 * </p>
 *
 * <ul>
 *     <li>#RGB</li>
 *     <li>#RGBA</li>
 *     <li>#RRGGBBAA</li>
 * </ul>
 *
 * <p>
 *     This converter will use the web based hexadecimal intepretations if
 *     the value is prefixed with {@link #HEX_COLOR_PREFIX}.
 *
 *     If using a literal number, or {@link Color#decode(String)} is desired, you must
 *     prefix your value with <code>0x</code> instead of {@link #HEX_COLOR_PREFIX}.
 * </p>
 *
 * @author seth@elypia.org (Seth Falco)
 * @since 1.0.0
 */
public class ColorConverter implements ConfigResolver.Converter<Color> {

    private static final Logger logger = LoggerFactory.getLogger(ColorConverter.class);

    /** To be a web based hexadecimal color, it must be prefixed with this. */
    private static final String HEX_COLOR_PREFIX = "#";

    /**
     * <p>
     *     Convert the configuration value to a Java {@link Color} object,
     *     by reading the hexadecimal {@link String} and converting each component.
     * </p>
     *
     * <p>
     *     This can also interpret raw color names based on the standard colors
     *     defined in Java, such as the following:
     * </p>
     *
     * <ul>
     *     <li>{@link Color#WHITE}</li>
     *     <li>{@link Color#LIGHT_GRAY}</li>
     *     <li>{@link Color#GRAY}</li>
     *     <li>{@link Color#DARK_GRAY}</li>
     *     <li>{@link Color#BLACK}</li>
     *     <li>{@link Color#RED}</li>
     *     <li>{@link Color#PINK}</li>
     *     <li>{@link Color#ORANGE}</li>
     *     <li>{@link Color#YELLOW}</li>
     *     <li>{@link Color#GREEN}</li>
     *     <li>{@link Color#MAGENTA}</li>
     *     <li>{@link Color#CYAN}</li>
     *     <li>{@link Color#BLUE}</li>
     * </ul>
     *
     * <small>
     *     Implementation Notes: We specifically avoid the use of {@link Color#decode(String)}
     *     for hexadecimal {@link String}s starting with {@link #HEX_COLOR_PREFIX}
     *     as it does not provide the desired result.
     *     The {@link Color#decode(String)} method uses {@link Integer#decode(String)}
     *     under the hood to convert the input to a number, which means input like
     *     <code>#FFF</code> gets interpretted incorrectly as it's literally converted
     *     to the number <code>0xFFF</code>, rather than the color, <code>#FFFFFF</code> which
     *     it is short hand for. It also doesn't work for <code>#FFFFFFFF</code> due to it
     *     being unable to parse to an {@link Integer}.
     *     If this is desired, then this method falls back to using {@link Color#decode(String)},
     *     so for literal hexadecimal values you prefix it with <code>0x</code> instead of
     *     {@link #HEX_COLOR_PREFIX}.
     * </small>
     *
     * @param value The value of the configuration property.
     * @return A {@link Color} which represents the compiled configuration property.
     * @throws NullPointerException If the value is null.
     * @throws NumberFormatException If an invalid number is provided.
     */
    @Override
    public Color convert(String value) {
        Objects.requireNonNull(value, "Value can't be null.");

        switch (value.toLowerCase()) {
            case "white": return Color.WHITE;
            case "lightgray": return Color.LIGHT_GRAY;
            case "gray": return Color.GRAY;
            case "darkgray": return Color.DARK_GRAY;
            case "black": return Color.BLACK;
            case "red": return Color.RED;
            case "pink": return Color.PINK;
            case "orange": return Color.ORANGE;
            case "yellow": return Color.YELLOW;
            case "green": return Color.GREEN;
            case "magenta": return Color.MAGENTA;
            case "cyan": return Color.CYAN;
            case "blue": return Color.BLUE;
            default: // Do nothing.
        }

        if (value.startsWith(HEX_COLOR_PREFIX))
            return parseWebColor(value);

        return Color.decode(value);
    }

    /**
     * <small>
     *     Implementation Notes: This must return a {@link Color}; not a {@link Number}
     *     or {@link String} for {@link Color#decode(String)} as it is not capable of supporing
     *     a color alpha channel due to the limited range of an {@link Integer}.
     * </small>
     *
     * @param value The web friendly hexadecimal {@link String}.
     * @return The Java friendly {@link Color} this color represents.
     * @throws NumberFormatException If the hexadecimal input contains non parsable characters.
     */
    public Color parseWebColor(final String value) {
        Objects.requireNonNull(value);

        final String hexadecimal = value.substring(HEX_COLOR_PREFIX.length());
        final int length = hexadecimal.length();

        final int[] rgba = new int[4];

        switch (length) {
            case 3:
                rgba[0] = Integer.parseInt(hexadecimal.substring(0, 1).repeat(2), 16);
                rgba[1] = Integer.parseInt(hexadecimal.substring(1, 2).repeat(2), 16);
                rgba[2] = Integer.parseInt(hexadecimal.substring(2, 3).repeat(2), 16);
                rgba[3] = 0xFF;
                break;
            case 4:
                rgba[0] = Integer.parseInt(hexadecimal.substring(0, 1).repeat(2), 16);
                rgba[1] = Integer.parseInt(hexadecimal.substring(1, 2).repeat(2), 16);
                rgba[2] = Integer.parseInt(hexadecimal.substring(2, 3).repeat(2), 16);
                rgba[3] = Integer.parseInt(hexadecimal.substring(3, 4).repeat(2), 16);
                break;
            case 6:
                rgba[0] = Integer.parseInt(hexadecimal.substring(0, 2), 16);
                rgba[1] = Integer.parseInt(hexadecimal.substring(2, 4), 16);
                rgba[2] = Integer.parseInt(hexadecimal.substring(4, 6), 16);
                rgba[3] = 0xFF;
                break;
            case 8:
                rgba[0] = Integer.parseInt(hexadecimal.substring(0, 2), 16);
                rgba[1] = Integer.parseInt(hexadecimal.substring(2, 4), 16);
                rgba[2] = Integer.parseInt(hexadecimal.substring(4, 6), 16);
                rgba[3] = Integer.parseInt(hexadecimal.substring(6, 8), 16);
                break;
            default:
                throw new IllegalArgumentException("Invalid hexadecimal color provided, if literal value decoding is required, specify 0x instead of #, otherwise expecting 3, 4, 6, or 8 characters only.");
        }

        return new Color(rgba[0], rgba[1], rgba[2], rgba[3]);
    }
}