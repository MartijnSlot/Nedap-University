package week4.test;

import org.junit.Test;

import week4.math.Exponent;
import week4.math.LinearProduct;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ExponentTest {

    public static final int CONSTANT_VALUE = 35;
    private static final double DELTA = 1e-15;

    @Test
    public void testApply() {
        assertEquals(1, new Exponent(0).apply(CONSTANT_VALUE), DELTA);
        assertEquals(35, new Exponent(1).apply(CONSTANT_VALUE), DELTA);
        assertEquals(1225, new Exponent(2).apply(CONSTANT_VALUE), DELTA);
    }

    @Test
    public void testDerivative() {
        Exponent exponent = new Exponent(3);
        assertEquals(12, exponent.derivative().apply(2), DELTA);
        assertTrue(exponent.derivative() instanceof LinearProduct);

    }
}
