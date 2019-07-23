import java.util.Iterator;

/**
 * FiniteIterator class. Outline of an iterator that steps a finite number of times.
 *
 * @param <T> The data type that the function for the iterator will take
 * @author Hunter Abraham
 */
public class FiniteIterator<T> implements Iterator<T> {
  private InfiniteIterator<T> it; // The infinite iterator
  private int length; // The length of the string
  private T val; // Current value in the infinite iterator
  private int count; // Tracks how many calls of next() have happened

  /**
   * The FiniteIterator constructor. Sets a value for length, the number of iterations that the
   * iterator will perform.
   *
   * @param it     The iterator for the class
   * @param length The number of iterations that the iterator will perform
   */
  public FiniteIterator(InfiniteIterator<T> it, int length) {
    this.it = it;
    this.length = length;
    this.count = 0;
  }

  /**
   * Applies the function to the current value. Returns the current value.
   *
   * @return The current value
   */
  public T next() {
    val = it.next();
    count++;
    return val;
  }

  /**
   * Determines if the number of iterations has reached the desired number.
   *
   * @return If the number of iterations has been reached, return false. Otherwise, return true.
   */
  public boolean hasNext() {
    if (count >= length)
      return false;

    return true;
  }
}
