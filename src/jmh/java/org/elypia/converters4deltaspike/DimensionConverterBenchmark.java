package org.elypia.converters4deltaspike;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

import java.awt.Dimension;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@State(Scope.Thread)
public class DimensionConverterBenchmark {

    private static final Pattern DIMENSION_PATTERN = Pattern.compile("(?<x>\\d+)(?:x(?<y>\\d+))?");

    @Benchmark
    public void convertRegardless() {
        convertRegardless("512");
    }

    @Benchmark
    public void convertOnlyIfNotEqual() {
        convertOnlyIfNotEqual("512");
    }

    public Dimension convertRegardless(String value) {
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

    public Dimension convertOnlyIfNotEqual(String value) {
        Objects.requireNonNull(value, "Dimensions can not be null.");

        if (value.isEmpty())
            throw new IllegalArgumentException("Dimensions can not be empty.");

        Matcher matcher = DIMENSION_PATTERN.matcher(value);

        if (!matcher.matches())
            throw new IllegalArgumentException("Dimension doesn't match format: {width/height} or {width}x{height}");

        String x = matcher.group(1);
        String y = matcher.group(2);

        int xValue = Integer.parseInt(x);
        int yValue = (y == null || x.equals(y)) ? xValue : Integer.parseInt(y);

        return new Dimension(xValue, yValue);
    }
}
