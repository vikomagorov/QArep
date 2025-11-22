package org.example;

import org.testng.annotations.Test;
import static org.testng.Assert.*;

public class TriangleAreaTest {

    @Test
    public void area345_is6() {
        assertEquals(Main.triangleArea(3,4,5), 6.0, 1e-9);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void nonTriangle_throwsIAE() {
        Main.triangleArea(1,2,3);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void negativeSide_throwsIAE() {
        Main.triangleArea(-1,2,2);
    }
}
