package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TriangleAreaTest {

    @Test
    void area345_is6() {
        assertEquals(6.0, Main.triangleArea(3, 4, 5), 1e-9);
    }

    @Test
    void nonTriangle_throwsIAE() {
        assertThrows(IllegalArgumentException.class, () -> Main.triangleArea(1, 2, 3));
    }

    @Test
    void negativeSide_throwsIAE() {
        assertThrows(IllegalArgumentException.class, () -> Main.triangleArea(-1, 2, 2));
    }
}
