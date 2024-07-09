package nl.vea.functionalp.examples.m03;

import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Utility {
    public static IntStream generateFibonacciSequence() {
        // To be implemented: Proper implementation.
        return Stream.iterate(new int[]{1,1}, t -> new int[]{t[1], t[0]+t[1]}).mapToInt(t -> t[0]);
    }
}
