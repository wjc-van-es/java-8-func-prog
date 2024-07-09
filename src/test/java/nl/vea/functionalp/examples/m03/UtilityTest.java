package nl.vea.functionalp.examples.m03;

import org.junit.jupiter.api.Test;

import java.util.PrimitiveIterator;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class UtilityTest {
    @Test
    public void testThatTheFirstTwentyElementsAreCorrect() {
        assertArrayEquals(
                new int[] {1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144, 233, 377, 610, 987, 1597, 2584, 4181, 6765},
                Utility.generateFibonacciSequence().limit(20).toArray(),
                "The first twenty elements are incorrect!");
    }

    @Test
    public void testRecursivePropertyInStreamByRandomLeaps() {
        for (int i = 0; i < 3; i++) { // Repeat three times
            final PrimitiveIterator.OfInt iterator =
                    Utility.generateFibonacciSequence()
                            .skip((int) (Math.random() * 900)) // Begin leap
                            .limit(20) // End leap
                            .iterator();
            int previous = iterator.nextInt(),
                    current = iterator.nextInt();
            while (iterator.hasNext()) {
                final int next = iterator.next();
                if (next != previous + current)
                    fail(String.format("Elements %s, %s are followed by %s!", previous, current, next));
                previous = current;
                current = next;
            }
        }
    }
}
