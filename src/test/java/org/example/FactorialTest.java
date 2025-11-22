package org.example;

import org.testng.annotations.Test;
import static org.testng.Assert.*;

public class FactorialTest {

    @Test
    public void factorialOf0_is1() {
        assertEquals(Main.factorial(0), 1L);
    }

    @Test
    public void factorialOf5_is120() {
        assertEquals(Main.factorial(5), 120L);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void factorialNegative_throwsIAE() {
        Main.factorial(-1);
    }

    @Test(expectedExceptions = ArithmeticException.class)
    public void factorialOverflow_throwsArithmetic() {
        Main.factorial(21);
    }
}
