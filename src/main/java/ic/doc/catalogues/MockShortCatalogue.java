package ic.doc.catalogues;

import static ic.doc.catalogues.QueryParser.firstNameFrom;
import static ic.doc.catalogues.QueryParser.lastNameFrom;
import static ic.doc.catalogues.QueryParser.publishedAfterFrom;
import static ic.doc.catalogues.QueryParser.publishedBeforeFrom;
import static ic.doc.catalogues.QueryParser.titleFrom;

import ic.doc.Book;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class MockShortCatalogue implements Searchable {

  private static MockShortCatalogue instance = new MockShortCatalogue();

  private final Collection<Book> catalogue = allTheBooks();

  private MockShortCatalogue() {}

  public static MockShortCatalogue getInstance() {
    return instance;
  }

  @Override
  public List<Book> searchFor(String query) {
    return catalogue.stream()
            .filter(book -> book.matchesAuthor(lastNameFrom(query)))
            .filter(book -> book.matchesAuthor(firstNameFrom(query)))
            .filter(book -> book.matchesTitle(titleFrom(query)))
            .filter(book -> book.publishedSince(publishedAfterFrom(query)))
            .filter(book -> book.publishedBefore(publishedBeforeFrom(query)))
            .collect(Collectors.toList());
  }

  private Collection<Book> allTheBooks() {

    System.out.println("Memory Usage: 500MB...");

    return Arrays.asList(
            new Book("A Tale of Two Cities", "Charles Dickens", 1859),
            new Book("Pride and Prejudice", "Jane Austen", 1813),
            new Book("Pride and Prejudice", "Jane Austen", 1813));
  }
}

