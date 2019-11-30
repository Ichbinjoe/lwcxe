package io.ibj.lwcxe.core;

import java.util.Collection;

/**
 * Stores values with points and allows for spacial querying for points. PointStore can: + Store
 * (and retrieve) multiple values at the same point + Store the same value at multiple different
 * points (if they show up in the same query, behavior is undefined as for whether or not the result
 * is returned once or twice) + Query values based on spacial questions (give me all values for
 * points in this region) + Query values on a point by point basis
 *
 * @param <T> Type of the point
 * @param <Q> Type of the value
 */
public interface PointStore<T extends Point<T>, Q> {
  /**
   * Inserts the value at the point. If another value is at the point, retrieval points will return
   * both, not just one - i.e. PointStore doesn't act like a set/map
   *
   * @param point Point to store the value under
   * @param value Value to store under the point
   */
  void insert(T point, Q value);

  /**
   * Inserts multiple value at the point. If other values are at the point, retrieval points will
   * return all values, not just these values - i.e. PointStore doesn't act like a set/map
   *
   * @param point Point to store the values under
   * @param value Values to store under the point
   */
  void insert(T point, Collection<Q> value);

  /**
   * Returns all values (if any) at a given point.
   *
   * @param point Query point
   * @return Iterable of all points which exist at the given query point
   */
  Iterable<Q> at(T point);

  /**
   * Returns a two-dimension array of all values with a point within the passed region (inclusive).
   *
   * @param region Region to query
   * @return two-dimension iterator of all values in the region
   */
  TDIterator<T, Q> in(Region<T> region);

  /**
   * Removes all values at a given point, removing any values which may have been present. Returns
   * an empty collection if no points were present.
   *
   * @param point point which to remove all values from
   */
  Collection<Q> removeAt(T point);

  /**
   * Removes all values in a region.
   *
   * @param region Region which to remove values from
   */
  void removeIn(Region<T> region);
}
