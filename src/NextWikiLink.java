import java.io.IOException;
import java.util.Iterator;
import java.util.Scanner;
import java.util.function.Function;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;


/**
 * NextWikiLink Class. Uses Generator.java, InfiniteIterator.java and FiniteIterator.java. Cycles
 * through wikipedia links.
 *
 * @author Hunter Abraham, Gary Dahl
 */
public class NextWikiLink implements Function<String, String> {
  /**
   * The function that runs apply() when called in the iterator's next() method.
   * Steps to the next wikipedia page.
   *
   * @param t The string of the current wikipedia address.
   * @return the string containing the address of the next wikipedia address.
   */
  @Override
  public String apply(String t) {
    try {
      // Download a Wikipedia page, using t in their internal link format: /wiki/Some_Subject
      Document doc = Jsoup.connect("https://en.wikipedia.org" + t).get();
      // Use .css selector to retrieve a collection of links from this page's description
      // "p a" selects links within paragraphs
      // ":not([title^='Help'])" skips pronunciations
      // ":not(sup a)" skips citations
      Elements links = doc.select("p a:not([title^='Help']):not(sup a)");
      // return the link attribute from the first element of this list
      return links.get(0).attr("href");
      // Otherwise return an appropriate error message:
    } catch (IOException | IllegalArgumentException e) {
      return "FAILED to find wikipedia page: " + t;
    } catch (IndexOutOfBoundsException e) {
      return "FAILED to find a link in wikipedia page: " + t;
    }
  }

  /**
   * Main method for NextWikiLink.java. Uses InfiniteIterator, FiniteIterator, Generator, and
   * NextWikiLink classes. Takes a user input and converts it into a usable link. Then, it prompts
   * for the number of iterations. Next, it cycles through each iteration, moving from page to page,
   * printing each one.
   *
   * @param args unused.
   */
  public static void main(String[] args) {
    // Implement your own Wikipedia crawling application here.
    // 1. prompt the user to enter a topic name and number of iterations to follow
    // 2. prepend "/wiki/" to the user's input, and replace spaces with underscores 
    // 3. use a for-each loop to iterate through the number of links requested
    Scanner scnr = new Scanner(System.in);
    System.out.println("Enter a wikipedia page topic: ");
    String nextPage = scnr.nextLine(); // Store user page
    for (int i = 0; i < nextPage.length(); i++) { // Remove spaces, replace with "_"
      if (Character.isWhitespace(nextPage.charAt(i))) {
        nextPage = nextPage.substring(0, i) + "_" + nextPage.substring((i + 1));
      }
    }
    nextPage = "/wiki/" + nextPage; // Prepend "/wiki/" to user input
    System.out.println("Enter the number of pages you'd like to step through: ");
    int numIterations = scnr.nextInt();
    // Create generator for finite iterator
    Generator<String> generator = new Generator<>(nextPage, new NextWikiLink(), numIterations);
    // Create iterator and step through until hasNext() is false
    for (Iterator it = generator.iterator(); it.hasNext(); ) {
      Object str = it.next();
      String str2 = (String) str;
      // If the iteration prints an error message, return false.
      if (str2.contains("FAILED to find a link in wikipedia page: ") || str2.contains("FAILED to " +
          "find wikipedia page: ")) {
        System.out.println(str2);
        break;
      }
      System.out.println(str2); // Print current page
    }
  }
}
