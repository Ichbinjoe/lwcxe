package io.ibj.lwcxe.core;

/**
 * A region is made up of two points in a given domain, lower and upper.
 *
 * @param <PointType> Type of the point
 */
public class Region<PointType extends Point> implements Pointed<PointType> {
  private final PointType lower;
  private final PointType upper;

  /**
   * (unsafe) Creates a new Region without validating any of the parameters
   * @param lower Lower point
   * @param upper Upper point
   */
  public Region(PointType lower, PointType upper) {
    this.lower = lower;
    this.upper = upper;
  }

  /**
   * Creates a new region validating the parametsr
   * @param lower Lower point
   * @param upper Upper point
   * @param <PointType> Type of the point
   * @return Region with lower & upper points
   *
   * @throws IllegalArgumentException if the arguments are not compatible with each other
   */
  public static <PointType extends Point> Region<PointType> create(
      PointType lower, PointType upper) {
    // check to be sure the points are of the same degree
    if (lower.degree() != upper.degree()) throw new IllegalArgumentException("Mismatched degrees");

    // check to be sure lower is actually lower than upper
    if (lower.compareTo(upper) > 0) throw new IllegalArgumentException("lower > upper");
    return new Region<>(lower, upper);
  }

  /**
   * Lower point
   * @return lower point
   */
  public PointType lower() {
    return this.lower;
  }

  /**
   * Upper point
   * @return upper point
   */
  public PointType upper() {
    return this.upper;
  }

  /**
   * Returns whether or not the passed point is contained by this region
   * @param point point which to test
   * @return Whether point is contained by this region
   */
  public boolean contains(PointType point) {
    return this.lower().compareTo(point) <= 0 && this.upper().compareTo(point) >= 0;
  }

  /**
   * Returns whether or not the passed region is _completely_ contained by this region. If the regions only intersect
   * (but both have their independent areas) this method will return false.
   * @param r region which to test for containment
   * @return Whether r is contained by this region
   */
  public boolean contains(Region<PointType> r) {
    // if our lower is lower than their lower and our higher is higher than their higher, we can assert that we
    // contain them
    return this.lower.compareTo(r.lower()) <= 0 && this.upper.compareTo(r.upper()) >= 0;
  }

  @Override
  public PointType point() {
    return lower;
  }
}
