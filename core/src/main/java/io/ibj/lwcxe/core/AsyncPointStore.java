package io.ibj.lwcxe.core;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;

import java.util.Collection;
import java.util.Map;

/**
 * Stores values with points in an asynchronous fashion. Typically this is to front some sort of
 * database interface or some other long-running query.
 *
 * <p>For write operations (insertions & removals) consistency isn't guaranteed until the method's
 * Observable returns.
 *
 * @param <T> Type of the point
 * @param <Q> Type of the value
 */
public interface AsyncPointStore<T extends Point<T>, Q> {
  /**
   * Inserts a point asynchronously into the backing data store.
   *
   * @param point Point to insert the value under
   * @param value Value to insert
   * @return Single void affirming the items insertion
   */
  Single<Void> insert(T point, Q value);

  /**
   * Inserts a set of key value pairs asynchronously into the backing data store.
   *
   * @param values Value pairs to insert
   * @return Single void affirming the items insertion
   */
  Single<Void> insert(Collection<Map.Entry<T, Q>> values);

  /**
   * Returns an observable of elements which are present at the given point.
   *
   * @param point Point to query for
   * @return Observable of all elements at point point
   */
  Observable<Q> at(T point);

  /**
   * Returns an observable of elements which are present within the given region.
   *
   * @param region Region to query for
   * @return Observable of all elements in Region region
   */
  Observable<Map.Entry<T, Q>> in(Region<T> region);

  /**
   * Removes all values at a given point.
   * @param point Point to remove values for
   * @return Single void affirming the items removal
   */
  Single<Void> removeAt(T point);

  /**
   * Removes all values are within a given region.
   * @param region Region to remove values from
   * @return Single void affirming the items removal
   */
  Single<Void> removeIn(Region<T> region);
}
