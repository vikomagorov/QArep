package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class FactorialTest {

    @Test
    void factorialOf0_is1() {
        assertEquals(1L, Main.factorial(0));
    }

    @Test
    void factorialOf5_is120() {
        assertEquals(120L, Main.factorial(5));
    }

    @Test
    void factorialNegative_throwsIAE() {
        assertThrows(IllegalArgumentException.class, () -> Main.factorial(-1));
    }

    @Test
    void factorialOverflow_throwsArithmetic() {
        assertThrows(ArithmeticException.class, () -> Main.factorial(21));
    }
}