package org.example;

import org.testng.annotations.Test;
import static org.testng.Assert.*;

public class IntCompareTest {

    @Test public void cmp_less_isMinus1() { assertEquals(Main.cmp(2,3), -1); }

    @Test public void cmp_equal_is0() { assertEquals(Main.cmp(5,5), 0); }

    @Test public void cmp_greater_is1() { assertEquals(Main.cmp(7,3), 1); }
}
