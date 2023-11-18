package nl.vea.functionalp.examples.m01;

import static nl.vea.functionalp.examples.m01.Color.GREEN;
import static nl.vea.functionalp.examples.m01.Color.RED;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class FilteringApplesVersion02 {

    public static void main(String... args) {
        List<Apple> inventory = Apple.getInventory();

        // print whole inventory
        System.out.println(inventory);

        /**
         *  get all green apples
         * here the injected {@link Predicate<Apple>} function is a reference to the
         * implementing class.
         */
        List<Apple> greenApples = filterApples(inventory, IsGreenApplePredicate);
        System.out.println(greenApples);

        /**
         *  get all red apples
         * here the injected {@link Predicate<Apple>} function is an anonymous inner class
         * definition as we were used to before Java 8 lambda expressions
         */
        List<Apple> redApples = filterApples(inventory, new Predicate<Apple>() {
            @Override
            public boolean test(Apple apple) {
                return RED.equals(apple.getColor());
            }
        });
        System.out.println(redApples);

        /**
         * get all heavy apples (with weight > 110)
         * here the injected {@link Predicate<Apple>} function is implemented by a lambda expression
         * or more specific the {@link Predicate#test(Apple)} method is.
         */
        List<Apple> heavyApples = filterApples(inventory, apple -> apple.getWeight() > 110);
        System.out.println(heavyApples);

    }

    /**
     * A generic method to filter Apples, whereby the filter criterium is parameterized
     *
     * @param inventory the list of {@Apple} items that needs to be filtered
     * @param predicate injects a suitable filter criterium as a function
     * @return a new list of {@Apple} items that all match the filter criterium.
     */
    public static List<Apple> filterApples(List<Apple> inventory, Predicate<Apple> predicate) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple : inventory) {
            if (predicate.test(apple)) {
                result.add(apple);
            }
        }
        return result;
    }

    private static Predicate<Apple> IsGreenApplePredicate = new Predicate<Apple>() {
        @Override
        public boolean test(Apple apple) {
            return GREEN.equals(apple.getColor());
        }
    };

}
