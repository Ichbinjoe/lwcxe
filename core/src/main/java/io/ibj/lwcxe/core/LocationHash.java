package io.ibj.lwcxe.core;

public class LocationHash {
    public static long hash(int shift, int hashlen, long... elements) {
        int realshift = (64 - (hashlen + shift));
        if (realshift != 0) {
            for (int i = 0; i < elements.length; i++) {
                elements[i] <<= realshift;
            }
        }

        long out = 0;
        for (int i = 0; i < hashlen; i++) {
            for (int j = 0; j < elements.length; j++) {
                out <<= 1;
                out |= (elements[j] & 0x8000000000000000L) >>> 63;
                elements[j] <<= 1;
            }
        }
        return out;
    }

    public static long scale_unsigned(int i) {
        return ((long) i) - Integer.MIN_VALUE;
    }

    public static int scale_signed(long i) {
        return (int)(i + Integer.MIN_VALUE);
    }
}
