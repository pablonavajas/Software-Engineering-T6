package ic.doc;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import ic.doc.catalogues.BookSearchQueryBuilder;
import ic.doc.catalogues.MockShortCatalogue;
import java.util.List;
import org.junit.Test;

public class BookSearchQueryTest {

  @Test
  public void searchesForBooksInLibraryCatalogueByAuthorSurname() {

    List<Book> books = new BookSearchQuery(null, "dickens", null, null, null).execute();

    assertThat(books.size(), is(2));
    assertTrue(books.get(0).matchesAuthor("dickens"));
  }

  @Test
  public void searchesForBooksInLibraryCatalogueByAuthorFirstname() {

    List<Book> books = new BookSearchQuery("Jane", null, null, null, null).execute();

    assertThat(books.size(), is(2));
    assertTrue(books.get(0).matchesAuthor("Austen"));
  }

  @Test
  public void searchesForBooksInLibraryCatalogueByTitle() {

    List<Book> books = new BookSearchQuery(null, null, "Two Cities", null, null).execute();

    assertThat(books.size(), is(1));
    assertTrue(books.get(0).matchesAuthor("dickens"));
  }

  @Test
  public void searchesForBooksInLibraryCatalogueBeforeGivenPublicationYear() {

    List<Book> books = new BookSearchQuery(null, null, null, null, 1700).execute();

    assertThat(books.size(), is(1));
    assertTrue(books.get(0).matchesAuthor("Shakespeare"));
  }

  @Test
  public void searchesForBooksInLibraryCatalogueAfterGivenPublicationYear() {

    List<Book> books = new BookSearchQuery(null, null, null, 1950, null).execute();

    assertThat(books.size(), is(1));
    assertTrue(books.get(0).matchesAuthor("Golding"));
  }

  @Test
  public void searchesForBooksInLibraryCatalogueWithCombinationOfParameters() {

    List<Book> books = new BookSearchQuery(null, "dickens", null, null, 1840).execute();

    assertThat(books.size(), is(1));
    assertTrue(books.get(0).matchesAuthor("charles dickens"));
  }

  @Test
  public void searchesForBooksInLibraryCatalogueWithCombinationOfTitleAndOtherParameters() {

    List<Book> books = new BookSearchQuery(null, null, "of", 1800, 2000).execute();

    assertThat(books.size(), is(3));
    assertTrue(books.get(0).matchesAuthor("charles dickens"));
  }

  /************************
   *    ADDED TESTS       *
   ************************/

  @Test
  public void searchesForBooksInLibraryCatalogueByAuthorSurnameUsingBuilder() {

    List<Book> books = BookSearchQueryBuilder.bookSearch()
            .withLastName("dickens").build().execute();

    assertThat(books.size(), is(2));
    assertTrue(books.get(0).matchesAuthor("dickens"));
  }

  @Test
  public void searchesForBooksInLibraryCatalogueByAuthorFirstNameUsingBuilder() {

    List<Book> books = BookSearchQueryBuilder.bookSearch().withFirstName("Jane")
            .build().execute();

    assertThat(books.size(), is(2));
    assertTrue(books.get(0).matchesAuthor("Austen"));
  }

  @Test
  public void searchesForBooksInLibraryCatalogueByTitleUsingBuilder() {

    List<Book> books = BookSearchQueryBuilder.bookSearch().withTitle("Two Cities")
            .build().execute();

    assertThat(books.size(), is(1));
    assertTrue(books.get(0).matchesAuthor("dickens"));
  }

  @Test
  public void searchesForBooksInLibraryCatalogueBeforeGivenPublicationYearUsingBuilder() {

    List<Book> books = BookSearchQueryBuilder.bookSearch().beforeDate(1700)
            .build().execute();

    assertThat(books.size(), is(1));
    assertTrue(books.get(0).matchesAuthor("Shakespeare"));
  }

  @Test
  public void searchesForBooksInLibraryCatalogueAfterGivenPublicationYearUsingBuilder() {

    List<Book> books = BookSearchQueryBuilder.bookSearch().afterDate(1950)
            .build().execute();

    assertThat(books.size(), is(1));
    assertTrue(books.get(0).matchesAuthor("Golding"));
  }

  @Test
  public void searchesForBooksInLibraryCatalogueWithCombinationOfParametersUsingBuilder() {

    List<Book> books = BookSearchQueryBuilder.bookSearch().withLastName("dickens")
            .beforeDate(1840).build().execute();

    assertThat(books.size(), is(1));
    assertTrue(books.get(0).matchesAuthor("charles dickens"));
  }

  @Test
  public void searchesForBooksInLibraryCatalogueWithTitleAndOtherParametersUsingBuilder() {

    List<Book> books = BookSearchQueryBuilder.bookSearch().withTitle("of")
            .afterDate(1800).beforeDate(2000).build().execute();

    assertThat(books.size(), is(3));
    assertTrue(books.get(0).matchesAuthor("charles dickens"));
  }

  @Test
  public void testingInIsolationUsingUsingAShortMockCatalogue() {

    List<Book> books = BookSearchQueryBuilder.bookSearch().withLastName("dickens")
            .build().changeCatalogue(MockShortCatalogue.getInstance()).execute();
    assertThat(books.size(), is(1));
    assertTrue(books.get(0).matchesAuthor("dickens"));
  }

}
