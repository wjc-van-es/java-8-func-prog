package nl.vea.functionalp.examples.m01;

import static nl.vea.functionalp.examples.m01.Color.GREEN;
import static nl.vea.functionalp.examples.m01.Color.RED;

import java.util.ArrayList;
import java.util.List;

public class FilteringApplesVersion01 {

    public static void main(String... args) {
        List<Apple> inventory = Apple.getInventory();

        // print whole inventory
        System.out.println(inventory);

        // get all green apples
        List<Apple> greenApples = filterGreenApples(inventory);
        System.out.println(greenApples);

        //get all red apples
        List<Apple> redApples = filterRedApples(inventory);
        System.out.println(redApples);

        // get all heavy apples (with weight > 110)
        List<Apple> heavyApples = filterHeavyApples(inventory);
        System.out.println(heavyApples);

    }

    public static List<Apple> filterGreenApples(List<Apple> inventory) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple : inventory) {
            if (GREEN.equals(apple.getColor())) {
                result.add(apple);
            }
        }
        return result;
    }

    public static List<Apple> filterRedApples(List<Apple> inventory) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple : inventory) {
            if (RED.equals(apple.getColor())) {
                result.add(apple);
            }
        }
        return result;
    }

    public static List<Apple> filterHeavyApples(List<Apple> inventory) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple : inventory) {
            if (apple.getWeight() > 110) {
                result.add(apple);
            }
        }
        return result;
    }

}
