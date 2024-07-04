package nl.vea.functionalp.examples.m03;

import static nl.vea.functionalp.examples.m04.Dish.MENU;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import nl.vea.functionalp.examples.m04.Dish;

public class Reducing {


    public static void main(String... args) {
        List<Integer> numbers = Arrays.asList(3, 4, 5, 1, 2);

        // The first parameter of reduce is called the identity parameter
        // It represents a neutral value that would be returned when the stream produces no elements
        // moreover, its value will not modify the result of the operation on any of the elements of the stream
        // hence, for a summation the identity value would be 0 as this wouldn't change the outcome of adding up any
        // elements of the stream.
        // for a multiplication 0 as identity value would render the final product 0 as well, so that would be a wrong
        // choice; instead an identity value of 1 would not modify the final product of all stream elements
        int sum = numbers.stream().reduce(0, (a, b) -> a + b);
        System.out.println(sum);

        int sum2 = numbers.stream().reduce(0, Integer::sum);
        System.out.println(sum2);

        // poorly chosen identity value of 0 it will render the product 0, whatever the element's values are
        int wreckedProduct = numbers.stream().reduce(0, (a, b) -> a * b);
        System.out.println(wreckedProduct);

        // The identity value of 1 is the proper neutral for a multiplication, it won't alter the product of the
        // stream's elements
        int product = numbers.stream().reduce(1, (a, b) -> a * b);
        System.out.println(product);

        long product1 = numbers.stream().reduce(1, Math::multiplyExact);
        System.out.println(product1);

        int max = numbers.stream().reduce(0, (a, b) -> Integer.max(a, b));
        System.out.println(max);

        Optional<Integer> min = numbers.stream().reduce(Integer::min);
        min.ifPresent(System.out::println);

        int calories = MENU.stream().map(Dish::getCalories).reduce(0, Integer::sum);
        System.out.println("Number of calories of all dishes on the menu:" + calories);
    }

}
