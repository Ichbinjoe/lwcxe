package io.ibj.lwcxe.core;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

public class LocationHashTest {
    private static Object[][] testLocationHashHashSource() {
        return new Object[][]{
                {0x0, 0, 32, new long[]{0x0, 0x0}},
                {0x3, 0, 32, new long[]{0x1, 0x1}},
                {0x6, 0, 32, new long[]{0x1, 0x2}},
                {0x7, 0, 21, new long[]{0x1, 0x1, 0x1}},
                {0x7, 1, 20, new long[]{0x2, 0x2, 0x2}},
                {0x7, 2, 19, new long[]{0x4, 0x4, 0x4}},
        };
    }

    @ParameterizedTest
    @MethodSource("testLocationHashHashSource")
    void testLocationHashHash(long result, int shift, int len, long[] elements) {
        Assertions.assertEquals(result, LocationHash.hash(shift, len, elements));
    }

    private static Object[][] testLocationHashHashPropsSource() {
        return new Object[][]{
                {1, 1, 1, 1, 1, 2},
                {1, 1, 1, 1, 2, 1},
                {1, 1, 1, 2, 1, 1},
        };
    }

    @ParameterizedTest
    @MethodSource("testLocationHashHashPropsSource")
    void testLocationHashHashProps(long x1, long y1, long z1, long x2, long y2, long z2) {
        Assertions.assertTrue(LocationHash.hash(0, 21, x1, y1, z1) < LocationHash.hash(0, 21, x2, y2, z2));
    }

    @Test
    void testScaling() {
        long last = Long.MAX_VALUE;
        for (long li = Integer.MIN_VALUE; li <= Integer.MAX_VALUE; li++) {
            int i = (int)li;
            long t = LocationHash.scale_unsigned(i);
            if (last != Long.MAX_VALUE) {
                Assertions.assertTrue(last < t);
            }

            last = t;
            Assertions.assertEquals(i, LocationHash.scale_signed(t));
        }
    }
}
