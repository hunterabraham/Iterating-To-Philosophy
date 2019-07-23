import java.util.Iterator;
import java.util.function.Function;

/**
 * Tests the EvenNumbers.java, InfiniteIterator.java, FiniteIterator.java, and Generator.java
 * classes.
 *
 * @author Hunter Abraham, Gary Dahl
 */
public class TestDriver {

  /**
   * Tests the EvenNumbers class
   *
   * @return true if EvenNumbers works correctly, false otherwise
   */
  public static boolean testEvenNumbers() {
    EvenNumbers it = new EvenNumbers(44); // Create new EvenNumbers object
    if (it.next() != 44) { // Check the first next() call is correct
      System.out.println("The first call of EvenNumbers.next() "
          + "did not return the value passed into its constructor.");
      return false; // If it isn't correct, return false
    }
    if (it.next() != 46) { // Check that the following next() call is correct
      System.out.println("The second call of EvenNumbers.next() "
          + "did not return the smallest even number, "
          + "that is larger than the previously returned number.");
      return false; // If it isn't correct, return false
    }
    if (!it.hasNext()) { // Check that the following next() call is correct
      System.out.println("EvenNumbers.next() returned false, "
          + "but should always return true.");
      return false; // If it isn't correct, return false
    }
    return true; // If all values are correct, return true
  }

  /**
   * Tests the powers of two function
   *
   * @return
   */
  public static boolean testPowersOfTwo() {
    // Create an infinite iterator with the PowerOfTwo function
    InfiniteIterator it = new InfiniteIterator(8, new NextPowerOfTwo());
    if (!it.next().equals(8)) { // Check that the it.next() will create the right value
      System.out.println("The first call of InfiniteIterator.next() "
          + "did not return the number passed into its constructor.");
      return false; // return false if the value created is incorrect
    }
    if (!it.next().equals(16)) { // Check that the it.next() will create the right value
      System.out.println("The second call of InfiniteIterator.next() "
          + "did not return the smallest power of two number, "
          + "that is larger than the previously returned number.");
      return false; // return false if the value created is incorrect
    }
    if (it.hasNext() != true) { // If it.hasNext() returns the wrong value,
      System.out.println("InfiniteIterator.next() returned false, "
          + "but should always return true.");
      return false; // return false and print error message.
    }
    return true; // If all tests pass, return true.
  }

  /**
   * Tests that the AddExtraSmile function is correct
   *
   * @return true if it runs correctly, false otherwise
   */
  public static boolean testAddExtraSmile() {
    // Create an infinite iterator that runs the AddExtraSmile function
    InfiniteIterator<String> it = new InfiniteIterator<>("Hello", new AddExtraSmile());
    if (!it.next().equals("Hello")) { // If the initial value is incorrect, return false
      System.out.println("The first call of InfiniteIterator.next() "
          + "did not return the string passed into its constructor.");
      return false;
    }
    if (!it.next().contains(" :)")) { // If the next value is not appended appropriately, return
      // false
      System.out.println("The second call of InfiniteIterator.next() "
          + "did not return the a string with one more :), "
          + "than the previously returned string.");
      return false;
    }
    if (it.hasNext() != true) { // If hasNext() is false, return false
      System.out.println("InfiniteIterator.next() returned false, "
          + "but should always return true.");
      return false;
    }
    return true; // If AddExtraSmile works correctly, return true.
  }

  /**
   * Tests the FiniteIterator class.
   *
   * @return true if the class behaves as expected, false otherwise
   */
  public static boolean testFiniteIterator() {
    // Create new infinite iterator to be used by the FiniteIterator
    InfiniteIterator<Integer> infinite = new InfiniteIterator<>(2, new NextPowerOfTwo());
    // Create new finite Iterator that uses the infinite iterator
    FiniteIterator<Integer> it = new FiniteIterator<>(infinite, 8);
    String s = ""; // Create string object to store the values create by next()
    while (it.hasNext()) // While the hasNext() returns true
      s += " " + it.next(); // Add the value to the string
    if (!s.equals(" 2 4 8 16 32 64 128 256")) { // If the values were created incorrectly,
      System.out.println("Repeatedly called the next() method of a FiniteIterator,"
          + "and the incorrect valuese were returned:" + s);
      return false; // return false
    }
    return true; // If it works correctly, return true.
  }


  /**
   * Tests the Generator class
   *
   * @return true if the class works correctly, false otherwise
   */
  public static boolean testGenerator() {
    // Create new generator
    Generator<Integer> generator = new Generator<>(2, new NextPowerOfTwo(), 8);
    Generator<Integer> generator1 = new Generator<>(2, new NextPowerOfTwo());
    String a = ""; // Creates String to store the values created by the finite generator
    for (Iterator it = generator.iterator(); it.hasNext(); ) { // While the iterator hasNext(),
      a = a + " " + it.next(); // add next() to the string
    }
    if (!a.equals(" 2 4 8 16 32 64 128 256")) { // If the values are incorrect, return false
      System.out.println("The finite iterator generated returned the incorrect value: " + a);
      return false;
    }
    return true; // If the generator works correctly, return true.

  }

  /**
   * main() method for the TestDriver class. Runs the test methods defined above.
   *
   * @param args unused.
   */
  public static void main(String[] args) {
    System.out.println("testEvenNumbers(): " + testEvenNumbers());
    System.out.println("testPowersOfTwo(): " + testPowersOfTwo());
    System.out.println("testAddExtraSmile(): " + testAddExtraSmile());
    System.out.println("testFiniteIterator(): " + testFiniteIterator());
    System.out.println("testGenerator(): " + testGenerator());
  }
}

/**
 * Function that generates powers of two
 *
 * @author Hunter Abraham, Gary Dahl
 */
class NextPowerOfTwo implements Function<Integer, Integer> {
  /**
   * Applies the function to the value. In this case, takes two times the current value
   *
   * @param previous The previous value to be multiplied by two
   * @return The next power of two
   */
  @Override
  public Integer apply(Integer previous) {
    return 2 * previous;
  }
}

/**
 * Function that adds a smiley face to the end of the string
 *
 * @author Hunter Abraham, Gary Dahl
 */
class AddExtraSmile implements Function<String, String> {
  /**
   * Applies the function to the value. In this case, takes a string and adds a smiley face to the
   * end.
   *
   * @param t The current string
   * @return The current string with a smiley face added to the end.
   */
  @Override
  public String apply(String t) {
    return t + " :)";
  }
}
