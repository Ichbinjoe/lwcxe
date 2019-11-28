package io.ibj.lwcxe.core;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Stream;

/**
 * A set of Pointed items of which all items' points have the same degree and hash (suggesting that
 * they co-exist at the same point in space)
 *
 * @param <T> Pointed type
 */
public class PointSet<T extends Pointed<Point>>
    implements Point, Collection<T> {

  private final Collection<T> c;

  /** Creates a new PointSet with the backing collection being a HashSet. */
  public PointSet() {
    this.c = new HashSet<>();
  }

  /**
   * (unsafe) Creates a new PointSet
   *
   * @param c Backing collection of the point set
   */
  public PointSet(Collection<T> c) {
    this.c = c;
  }

  /**
   * Creates a new PointSet while first validating all items within the PointSet collection
   *
   * @param c Backing collection of the point set
   * @param <T> Pointed type
   * @return Created / validated point set
   */
  public static <T extends Pointed<Point>>
      PointSet<T> create(Collection<T> c) {
    PointSet<T> p = new PointSet<>(c);
    Iterator<T> i = c.iterator();
    if (i.hasNext()) {
      Point first = i.next().point();
      while (i.hasNext()) {
        Point elem = i.next().point();
        if (first.degree() != elem.degree())
          throw new IllegalArgumentException("Degrees do not match");
        if (first.compareTo(elem) != 0)
          throw new IllegalArgumentException("Points compareTo is not 0 - elements not equivalent");
      }
    }
    return p;
  }

  private Optional<Point> firstElement() {
    Iterator<T> i = iterator();
    if (!i.hasNext()) return Optional.empty();
    return Optional.of(i.next().point());
  }

  public int degree() {
    return firstElement().map(Point::degree).orElse(0);
  }

  public int compareTo(Point point) {
    Iterator<T> i = this.c.iterator();
    if (!i.hasNext()) return 0;
    return i.next().point().compareTo(point);
  }

  public int compareTo(Pointed<Point> o) {
    return compareTo(o.point());
  }

  /**
   * Checks a point to ensure that it has the same degree & hash as the overarching set
   *
   * @param p point to validate
   * @throws IllegalArgumentException when the point does not match either the degree or hash of the
   *     set
   */
  protected void checkPoint(Point p) {
    Optional<Point> o = firstElement();
    if (!o.isPresent()) return;
    Point f = o.get();
    if (p.degree() != f.degree()) throw new IllegalArgumentException("Degrees do not match");
    if (p.compareTo(f) != 0)
      throw new IllegalArgumentException("Points compareTo is not 0 - elements not equivalent");
  }

  @Override
  public int size() {
    return c.size();
  }

  @Override
  public boolean isEmpty() {
    return c.isEmpty();
  }

  @Override
  public boolean contains(Object o) {
    return c.contains(o);
  }

  @Override
  public Iterator<T> iterator() {
    return c.iterator();
  }

  @Override
  public void forEach(Consumer<? super T> action) {
    c.forEach(action);
  }

  @Override
  public Object[] toArray() {
    return c.toArray();
  }

  @Override
  public <T1> T1[] toArray(T1[] a) {
    return c.toArray(a);
  }

  @Override
  public boolean add(T t) {
    Point p = t.point();
    checkPoint(p);
    return addUnchecked(t);
  }

  /**
   * (unsafe) Applies an add operation to the internal collection without validating the point being
   * added
   *
   * @param t Point to add to the internal collection
   * @return whatever add returns.
   */
  public boolean addUnchecked(T t) {
    return c.add(t);
  }

  @Override
  public boolean remove(Object o) {
    return c.remove(o);
  }

  @Override
  public boolean containsAll(Collection<?> c) {
    return this.c.containsAll(c);
  }

  @Override
  public boolean addAll(Collection<? extends T> c) {
    c.forEach(i -> checkPoint(i.point()));
    return this.c.addAll(c);
  }

  public boolean addAll(PointSet<? extends T> c) {
    this.checkPoint(c);
    return this.c.addAll(c);
  }

  /**
   * (unsafe) Applies an addAll operation to the internal collection without validating the points
   * being added
   *
   * @param c Collection containing points to add to the internal collection
   * @return whatever addAll returns.
   */
  public boolean addAllUnchecked(Collection<? extends T> c) {
    return this.c.addAll(c);
  }

  @Override
  public boolean removeAll(Collection<?> c) {
    return this.c.removeAll(c);
  }

  @Override
  public boolean removeIf(Predicate<? super T> filter) {
    return c.removeIf(filter);
  }

  @Override
  public boolean retainAll(Collection<?> c) {
    return this.c.retainAll(c);
  }

  @Override
  public void clear() {
    c.clear();
  }

  @Override
  public Spliterator<T> spliterator() {
    return c.spliterator();
  }

  @Override
  public Stream<T> stream() {
    return c.stream();
  }

  public Stream<T> parallelStream() {
    return c.parallelStream();
  }
}
