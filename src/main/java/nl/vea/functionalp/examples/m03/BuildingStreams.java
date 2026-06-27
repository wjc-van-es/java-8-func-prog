package nl.vea.functionalp.examples.m03;

import java.util.Arrays;
import java.util.function.IntSupplier;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class BuildingStreams {

    public static void main(String... args) throws Exception {
        // Stream.of
        Stream<String> stream = Stream.of("Java 8", "Lambdas", "In", "Action");
        stream.map(String::toUpperCase).forEach(System.out::println);

        // Stream.empty
        Stream<String> emptyStream = Stream.empty();

        // Arrays.stream
        int[] numbers = {2, 3, 5, 7, 11, 13};
        System.out.println(Arrays.stream(numbers).sum());

        // Stream.iterate
        Stream.iterate(0, n -> n + 2).limit(10) // without the limit the stream keeps processing integers ad infinitum
                .forEach(System.out::println);

        // Fibonacci with iterate
        System.out.println("Preparing Fibonacci with a pair seeded by an initial array with the first 2 elements {0, 1}");
        System.out.println("Each succeeding pair contains the second element of the previous as first and" +
                " the sum of both elements of the previous as second element");
        System.out.println("For now these pairs are returned and printed");
        Stream.iterate(new int[]{0, 1}, t -> new int[]{t[1], t[0] + t[1]}).limit(10)
                .forEach(t -> System.out.printf("(%d, %d)\n", t[0], t[1]));

        System.out.println("Creating the final Fibonacci by mapping each array to a single int by each time taking its first element");
        Stream.iterate(new int[]{0, 1}, t -> new int[]{t[1], t[0] + t[1]}).limit(11).mapToInt(t -> t[0])
                .forEach(System.out::println);

        // random stream of doubles with Stream.generate
        Stream.generate(Math::random).limit(10).forEach(System.out::println);

        // stream of 1s with Stream.generate
        IntStream.generate(() -> 1).limit(5).forEach(System.out::println);

        IntStream.generate(new IntSupplier() {
            @Override
            public int getAsInt() {
                return 2;
            }
        }).limit(5).forEach(System.out::println);

        System.out.println("Creating Fibonacci with an IntSupplier");
        // This supplier almost renders Fibonacci, but it starts with 1 instead of 0
        IntSupplier fib = new IntSupplier() {

            private int previous = 0;
            private int current = 1;

            @Override
            public int getAsInt() {
                int nextValue = previous + current;
                previous = current;
                current = nextValue;
                return previous;
            }

        };

        // Here we concat 2 InnStream to include the first Fibonacci element 0 as fib starts with the second element
        IntStream.concat(IntStream.of(0), IntStream.generate(fib).limit(10)).forEach(System.out::println);
    }

}
