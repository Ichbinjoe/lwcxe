package io.ibj.lwcxe.core;

/**
 * A point which can be compared with other points
 */
public interface Point extends Comparable<Point> {
  /**
   * Returns the 'degree' of this point - i.e. in how many dimensions it acts.
   *
   * @return degree of the point
   */
  int degree();
}
