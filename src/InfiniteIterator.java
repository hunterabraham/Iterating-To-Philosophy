import java.util.Iterator;
import java.util.function.Function;

/**
 * Outline for an iterator that will apply some function to a value.
 *
 * @param <T> The data type that the function will take
 * @author Hunter Abraham
 */
public class InfiniteIterator<T> implements Iterator<T> {

  private T currNum; // The current value
  private Function<T, T> function; // The function that the iterator will apply


  /**
   * The iterator constructor that will run the next() method and thus the apply() method for the
   * function
   *
   * @param currNum  The initial value that will
   * @param function The function that will be applied in next()
   */
  public InfiniteIterator(T currNum, Function<T, T> function) {
    this.currNum = currNum;
    this.function = function;
  }

  /**
   * Applies the function to the current value. Returns the current value.
   *
   * @return the current value stored in currNum
   */
  public T next() {
    T returnVal = currNum;
    currNum = function.apply(currNum);
    return returnVal;
  }

  /**
   * Checks if the iterator has a next value. Because this iterator is infinite, it will always
   * return true.
   *
   * @return true
   */
  public boolean hasNext() {
    return true;
  }

}
