package org.elypia.converters4deltaspike;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@State(Scope.Thread)
public class CharacterConverterBenchmark {

    private static final String HEX_PREFIX = "0x";

    private static final Pattern HEX_PATTERN = Pattern.compile(HEX_PREFIX + "[a-f\\d]+", Pattern.CASE_INSENSITIVE);

    @Benchmark
    public void benchmarkConvertWithStartsWithAndSubstring() {
        convertWithStartsWithAndSubstring("0xa3");
    }

    @Benchmark
    public void benchmarkConvertWithRegexCheckOnly() {
        convertWithRegexCheckOnly("0xa3");
    }

    public Character convertWithStartsWithAndSubstring(String value) {
        Objects.requireNonNull(value, "Value can't be null.");

        if (value.isEmpty())
            throw new IllegalArgumentException("Value can't be empty.");

        if (value.length() == 1)
            return value.charAt(0);

        if (value.toLowerCase().startsWith(HEX_PREFIX)) {
            int hexValue = Integer.parseInt(value, HEX_PREFIX.length(), value.length(), 16);
            return (char)hexValue;
        }

        throw new IllegalArgumentException("Value can't be represented as a character.");
    }

    public Character convertWithRegexCheckOnly(String value) {
        Objects.requireNonNull(value, "Value can't be null.");

        if (value.isEmpty())
            throw new IllegalArgumentException("Value can't be empty.");

        if (value.length() == 1)
            return value.charAt(0);

        if (HEX_PATTERN.matcher(value).matches()) {
            int hex = Integer.parseInt(value, HEX_PREFIX.length(), value.length(), 16);
            return (char)hex;
        }

        throw new IllegalArgumentException("Value can't be represented as a character.");
    }
}
