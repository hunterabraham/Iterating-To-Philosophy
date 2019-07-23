import java.util.Iterator;

/**
 * An iterator that generates even numbers
 *
 * @author Hunter Abraham
 */
public class EvenNumbers implements Iterator<Integer> {

  private Integer currNum;

  /**
   * The EvenNumbers constructor. Sets the initial value for the iterator.
   *
   * @param num The initial number for the iterator.
   */
  public EvenNumbers(Integer num) {
    this.currNum = num;
  }

  /**
   * Applies the function to the current value. Returns the current value.
   *
   * @return The current value.
   */
  public Integer next() {
    int tempNum = currNum;
    this.currNum += 2;
    return tempNum;
  }

  /**
   * Because it is an infinite iterator, hasNext() will always return true.
   *
   * @return true.
   */
  public boolean hasNext() {
    return true;
  }

}
