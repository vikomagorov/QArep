package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class IntCompareTest {

    @Test
    void cmp_less_isMinus1() {
        assertEquals(-1, Main.cmp(2, 3));
    }

    @Test
    void cmp_equal_is0() {
        assertEquals(0, Main.cmp(5, 5));
    }

    @Test
    void cmp_greater_is1() {
        assertEquals(1, Main.cmp(7, 3));
    }
}
