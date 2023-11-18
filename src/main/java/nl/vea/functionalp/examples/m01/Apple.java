package nl.vea.functionalp.examples.m01;

import static nl.vea.functionalp.examples.m01.Color.GREEN;
import static nl.vea.functionalp.examples.m01.Color.RED;

import java.util.Arrays;
import java.util.List;

public class Apple {

    private int weight = 0;
    private Color color;

    public Apple(int weight, Color color) {
        this.weight = weight;
        this.color = color;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    @SuppressWarnings("boxing")
    @Override
    public String toString() {
        return String.format("Apple{color=%s, weight=%d}", color, weight);
    }

    public static List<Apple> getInventory() {
        return Arrays.asList(
                new Apple(80, GREEN),
                new Apple(155, GREEN),
                new Apple(120, RED),
                new Apple(98, RED),
                new Apple(95, GREEN),
                new Apple(130, GREEN),
                new Apple(105, RED),
                new Apple(86, RED));
    }

}