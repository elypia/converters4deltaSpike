package org.elypia.converters4deltaspike;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

import java.awt.Color;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@State(Scope.Thread)
public class ColorConverterBenchmark {

    @Benchmark
    public void benchmarkSwitchToHandleAllLengthsWith3Digits() {
        switchToHandleAllLengths("#FFF");
    }

    @Benchmark
    public void benchmarkSwitchToHandleAllLengthsWith8Digits() {
        switchToHandleAllLengths("#FFFFFFFF");
    }

    @Benchmark
    public void benchmarkRegexToDoubleUpCharsWith3Digits() {
        regexToDoubleUpChars("#FFF");
    }

    @Benchmark
    public void benchmarkRegexToDoubleUpCharsForJava8With8Digits() {
        regexToDoubleUpChars("#FFFFFFFF");
    }

    public Color switchToHandleAllLengths(final String value) {
        Objects.requireNonNull(value);

        switch (value.length()) {
            case 4:
                return new Color(
                    Integer.parseInt(value.substring(1, 2), 16) * 17,
                    Integer.parseInt(value.substring(2, 3), 16) * 17,
                    Integer.parseInt(value.substring(3, 4), 16) * 17
                );
            case 5:
                return new Color(
                    Integer.parseInt(value.substring(1, 2), 16) * 17,
                    Integer.parseInt(value.substring(2, 3), 16) * 17,
                    Integer.parseInt(value.substring(3, 4), 16) * 17,
                    Integer.parseInt(value.substring(4, 5), 16) * 17
                );
            case 7:
                return new Color(
                    Integer.parseInt(value.substring(1, 3), 16),
                    Integer.parseInt(value.substring(3, 5), 16),
                    Integer.parseInt(value.substring(5, 7), 16)
                );
            case 9:
                return new Color(
                    Integer.parseInt(value.substring(1, 3), 16),
                    Integer.parseInt(value.substring(3, 5), 16),
                    Integer.parseInt(value.substring(5, 7), 16),
                    Integer.parseInt(value.substring(7, 9), 16)
                );
            default:
                throw new IllegalArgumentException("Invalid hexadecimal color provided, if literal value decoding is required, specify 0x instead of #, otherwise expecting 3, 4, 6, or 8 characters only.");
        }
    }

    public Color regexToDoubleUpChars(final String value) {
        Objects.requireNonNull(value);

        final int length = value.length();
        final String hexString = (length == 4 || length == 5) ? value.replaceAll("[^#]", "$0$0") : value;

        switch (hexString.length()) {
            case 7:
                return Color.decode(hexString);
            case 9:
                return new Color(
                    Integer.parseInt(hexString.substring(1, 3), 16),
                    Integer.parseInt(hexString.substring(3, 5), 16),
                    Integer.parseInt(hexString.substring(5, 7), 16),
                    Integer.parseInt(hexString.substring(7, 9), 16)
                );
            default:
                throw new IllegalArgumentException("Invalid hexadecimal color provided, if literal value decoding is required, specify 0x instead of #, otherwise expecting 3, 4, 6, or 8 characters only.");
        }
    }
}
