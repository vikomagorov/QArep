package org.example;

import org.testng.annotations.Test;
import static org.testng.Assert.*;

public class CalculatorTest {

    @Test public void add_ok() { assertEquals(Main.add(3,4), 7); }

    @Test public void sub_ok() { assertEquals(Main.sub(5,4), 1); }

    @Test public void mul_ok() { assertEquals(Main.mul(3,4), 12); }

    @Test public void div_ok() { assertEquals(Main.div(10,5), 2); }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void divByZero_throwsIAE() { Main.div(10, 0); }
}
