package nl.vea.functionalp.examples.m01;

import java.util.List;
import java.util.function.Predicate;

/**
 * This class weighs apples
 *
 * @author es00w05
 */
public class AppleScale {

    /**
     * all apples from the inventory that match the predicate are weighed and the total weight is returned.
     *
     * @param inventory
     * @param predicate
     * @return the total weight
     */
    public int weigh(List<Apple> inventory, Predicate<Apple> predicate) {
        return inventory.stream()
                .filter(predicate)
                .map(Apple::getWeight)
                .reduce(Integer::sum)
                .orElse(0);
    }
}
