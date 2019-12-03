package io.ibj.lwcxe.core;

import java.util.Iterator;
import java.util.Map;

/**
 * Special variation of an iterator which allows traversing over two dimensions - either value-wise or key-wise.
 * Traditional {@code Iterator} methods exist to iterate over the collection value wise (if a key has multiple values,
 * next will only progress the iteration to the next value in the key if it has another, otherwise to the next key).
 * These extended methods (hasNextKey(), nextKey(), removeKey()) operate on a per-key basis,
 * which may be more efficient for certain algorithms / operations.
 *
 * This also contains a 'key' iterator view, which can be used concurrently with this iterator (updating the key
 * iterator will update the state of this iterator).
 * @param <K> Key type
 * @param <V> Value type
 */
public interface TDIterator<K, V> extends Iterator<Map.Entry<K, V>> {
    /**
     * Returns whether there is another key within this iteration, indicating that nextKey may be called properly.
     * @return whether there is another key to iterate over
     */
    boolean hasNextKey();

    /**
     * Returns whether there is another value in this key. Algorithms may use this to be able to easily segment
     * different keys without needing to do bookkeeping themselves.
     * @return whether there is another value in this key
     */
    boolean hasNextValueInKey();

    /**
     * Continues iteration and returns the next key in the iteration. This leaves the normal iterator pointing at the
     * '0th' value - calling next() will return the first value.
     * @return Next key in the sequence
     */
    K nextKey();

    /**
     * Removes the current key and all values under said key
     */
    void removeKey();

}
