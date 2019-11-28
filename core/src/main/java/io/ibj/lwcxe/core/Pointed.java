package io.ibj.lwcxe.core;

/**
 * Describes an object which has a point which can be used as a key for this object
 * @param <Point> point type
 */
public interface Pointed<Point> {
    Point point();
}
