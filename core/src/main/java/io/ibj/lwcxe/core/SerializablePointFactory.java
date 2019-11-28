package io.ibj.lwcxe.core;

/**
 * A factory which can compose and decompose a point using its composite parts
 * @param <FactorType> Type of each of the factors
 * @param <PointType> Type of the composite point type
 */
public interface SerializablePointFactory<FactorType, PointType> {
  /**
   * Create a new PointType point given the various FactorTypes.
   * @param coordinates Point factors which to construct the point from
   * @return Point constructed from the various factors
   */
  PointType create(FactorType[] coordinates);

  /**
   * Decomposes a point into its various factors.
   * @param point Point which to decompose
   * @return Array of factors which make up this point
   */
  FactorType[] decompose(PointType point);
}
