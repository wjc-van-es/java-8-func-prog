package nl.vea.functionalp.examples.m01;

import org.junit.jupiter.api.Test;

import static nl.vea.functionalp.examples.m01.Color.GREEN;
import static org.junit.jupiter.api.Assertions.assertEquals;


/**
 * TODO: es00w05: beschrijf deze klasse !
 *
 * @author es00w05
 */
public class AppleScaleTest {

    AppleScale underTest = new AppleScale();

    @Test
    public void testWeightOfGreenApples() {
        assertEquals(460, underTest.weigh(Apple.getInventory(), apple -> GREEN.equals(apple.getColor())));
    }
}
