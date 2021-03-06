package week4.test;

import org.junit.Before;
import org.junit.Test;

import week4.math.Constant;
import week4.math.Product;
import week4.math.Sum;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ProductTest {

    private static final double DELTA = 1e-15;
    private static final double CONSTANT_VALUE = Math.PI;
    private Product product;

    @Before
    public void setUp() {
        product = new Product(new Constant(CONSTANT_VALUE), new Constant(CONSTANT_VALUE));
    }

    @Test
    public void testApply() {
        assertEquals(CONSTANT_VALUE * CONSTANT_VALUE, product.apply(0), DELTA);
        assertEquals(CONSTANT_VALUE * CONSTANT_VALUE, product.apply(-1), DELTA);
        assertEquals(CONSTANT_VALUE * CONSTANT_VALUE, product.apply(1), DELTA);
    }

    @Test
    public void testDerivative() {
        assertTrue(product.derivative() instanceof Sum);
        assertEquals(0.0, product.derivative().apply(0), DELTA);
    }
}
