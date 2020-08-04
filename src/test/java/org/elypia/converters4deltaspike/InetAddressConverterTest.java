package org.elypia.converters4deltaspike;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.net.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author seth@elypia.org (Seth Falco)
 * @since 1.0.0
 */
public class InetAddressConverterTest {

    @Test
    public void testConverteringIpv4() throws UnknownHostException {
        InetAddressConverter converter = new InetAddressConverter();

        final InetAddress expected = InetAddress.getByName("192.168.0.1");
        final InetAddress actual = converter.convert("192.168.0.1");

        assertEquals(expected, actual);
    }

    @Test
    public void testConverteringIpv6() throws UnknownHostException {
        InetAddressConverter converter = new InetAddressConverter();

        final InetAddress expected = InetAddress.getByName("2001:db8:0:1234:0:567:8:1");
        final InetAddress actual = converter.convert("2001:db8:0:1234:0:567:8:1");

        assertEquals(expected, actual);
    }

    @Test
    public void testConverteringLocalhost() throws UnknownHostException {
        InetAddressConverter converter = new InetAddressConverter();

        final InetAddress expected = InetAddress.getByName("127.0.0.1");
        final InetAddress actual = converter.convert("localhost");

        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @ValueSource(strings = {"Hello, world!", "512.512.512.512"})
    public void testConverteringInvalid(final String value) {
        InetAddressConverter converter = new InetAddressConverter();
        assertThrows(IllegalArgumentException.class, () -> converter.convert(value));
    }
}
