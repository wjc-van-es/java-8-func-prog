package nl.vea.functionalp.examples.m01;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static nl.vea.functionalp.examples.m01.Color.GREEN;
import static nl.vea.functionalp.examples.m01.Color.RED;

public class FilteringApplesVersion03 {

    public static void main(String... args) {
        List<Apple> inventory = Apple.getInventory();

        // print whole inventory
        System.out.println(inventory);

        // get all green apples
        List<Apple> greenApples = filterApples(inventory, IsGreenApplePredicate);
        System.out.println(greenApples);

        //get all red apples
        List<Apple> redApples = filterApples(inventory, apple -> RED.equals(apple.getColor()));
        System.out.println(redApples);

        // get all heavy apples (with weight > 110)
        List<Apple> heavyApples = filterApples(inventory, apple -> apple.getWeight() > 110);
        System.out.println(heavyApples);

        //Als het er alleen maar omgaat om alle groene appels te printen
        inventory.stream().filter(IsGreenApplePredicate).forEach(apple -> System.out.println(apple));

        //In dit geval kan je ook een method reference gebruiken i.p.v. lambda expressie
        inventory.stream().filter(IsGreenApplePredicate).forEach(System.out::println);

    }

    /**
     * A generic method to filter Apples, whereby the filter criterium is parameterized
     *
     * @param inventory the list of {@link Apple} items that needs to be filtered
     * @param predicate injects a suitable filter criterium as a function
     * @return a new list of {@link Apple} items that all match the filter criterium.
     */
    public static List<Apple> filterApples(List<Apple> inventory, Predicate<Apple> predicate) {
        return inventory
                .stream()
                .filter(predicate)
                .collect(Collectors.toList());
    }

    private static final Predicate<Apple> IsGreenApplePredicate = apple -> GREEN.equals(apple.getColor());

}
