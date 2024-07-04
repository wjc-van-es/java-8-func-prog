package nl.vea.functionalp.examples.m03;

import static java.util.stream.Collectors.toList;
import static nl.vea.functionalp.examples.m04.Dish.MENU;

import java.util.Arrays;
import java.util.List;

import nl.vea.functionalp.examples.m04.Dish;

public class Mapping {

    public static void main(String... args) {
        // map
        List<String> dishNames = MENU.stream().map(Dish::getName).collect(toList());
        System.out.println(dishNames);

        // map
        List<String> words = Arrays.asList("Hello", "World");
        List<Integer> wordLengths = words.stream().map(String::length).collect(toList());
        System.out.println(wordLengths);

        // flatMap
        words.stream() // creates a Stream containing to String objects "Hello" and "World"
                // Now the lambda expression creates a stream of Strings each containing the individual letters of each former word String
                // The flatMap function will flatten these distinct streams into a single one containing the single letter Strings
                .flatMap((String line) -> Arrays.stream(line.split("")))
                .distinct() // distinct will remove all duplicate letters from the stream
                .forEach(System.out::println);

        // flatMap
        List<Integer> numbers1 = Arrays.asList(1, 2, 3, 4, 5);
        List<Integer> numbers2 = Arrays.asList(6, 7, 8);
        List<int[]> pairs = numbers1.stream()
                // the lambda expression makes combinations of all elements of numbers1 with all elements of numbers2
                // flatMap flattens the resulting streams of streams of arrays to a single stream of arrays, where each array represents a combination
                .flatMap((Integer i) -> numbers2.stream().map((Integer j) -> new int[]{i, j}))
                .filter(pair -> (pair[0] + pair[1]) % 3 == 0) // here only arrays whose sum of elements are divisible by 3 are selected
                .collect(toList());
        pairs.forEach(pair -> System.out.printf("(%d, %d)", pair[0], pair[1]));
    }

}
