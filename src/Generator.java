import java.util.Iterator;
import java.util.function.Function;

/**
 * Creates an iterator for the user.
 *
 * @param <T> The data type for the Function applied in the iterator created.
 * @author Hunter Abraham
 */
public class Generator<T> implements Iterable<T> {

  private T firstValue; // The initial value given by the user
  private Function<T, T> generateNextFromLast; // The function to be applied in next()
  private int length; // The length for a finite iterator, the number of iterations

  /**
   * Generator constructor. Will create an infinite iterator in iterator()
   *
   * @param firstValue           The first value for the iterator
   * @param generateNextFromLast The function for the iterator to be created
   */
  public Generator(T firstValue, Function<T, T> generateNextFromLast) {
    this.firstValue = firstValue;
    this.generateNextFromLast = generateNextFromLast;
  }

  /**
   * Generator constructor. Will create a finite iterator in iterator()
   *
   * @param firstValue           The first value for the iterator
   * @param generateNextFromLast The function for the iterator to be created
   * @param length               The number of desired iterations for the iterator
   */
  public Generator(T firstValue, Function<T, T> generateNextFromLast, int length) {
    this.firstValue = firstValue;
    this.generateNextFromLast = generateNextFromLast;
    this.length = length;
  }


  /**
   * Creates an iterator. If the length value is not default value, create a finite iterator.
   * Otherwise, create an infinite iterator.
   *
   * @return The iterator created.
   */
  public Iterator<T> iterator() {
    if (length == 0) {
      return new InfiniteIterator<T>(firstValue, generateNextFromLast);
    }
    return new FiniteIterator<T>(new InfiniteIterator<T>(firstValue, generateNextFromLast), length);
  }


}
