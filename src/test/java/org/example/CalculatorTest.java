package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {

    @Test
    void add_ok() {
        assertEquals(7, Main.add(3, 4));
    }

    @Test
    void sub_ok() {
        assertEquals(1, Main.sub(5, 4));
    }

    @Test
    void mul_ok() {
        assertEquals(12, Main.mul(3, 4));
    }

    @Test
    void div_ok() {
        assertEquals(2, Main.div(10, 5));
    }

    @Test
    void divByZero_throwsIAE() {
        assertThrows(IllegalArgumentException.class, () -> Main.div(10, 0));
    }
}
