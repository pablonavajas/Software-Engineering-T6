package ic.doc.catalogues;

import ic.doc.BookSearchQuery;

public class BookSearchQueryBuilder {

  private String field1;
  private String field2;
  private String field3;
  private Integer field4;
  private Integer field5;

  private BookSearchQueryBuilder() {
    field1 = null;
    field2 = null;
    field3 = null;
    field4 = null;
    field5 = null;
  }

  public static BookSearchQueryBuilder bookSearch() {
    return new BookSearchQueryBuilder();
  }

  public BookSearchQuery build() {
    return new BookSearchQuery(field1, field2, field3, field4, field5);
  }

  public BookSearchQueryBuilder withFirstName(String field1) {
    this.field1 = field1;
    return this;
  }

  public BookSearchQueryBuilder withLastName(String field2) {
    this.field2 = field2;
    return this;
  }

  public BookSearchQueryBuilder withTitle(String field3) {
    this.field3 = field3;
    return this;
  }

  public BookSearchQueryBuilder afterDate(Integer field4) {
    this.field4 = field4;
    return this;
  }

  public BookSearchQueryBuilder beforeDate(Integer field5) {
    this.field5 = field5;
    return this;
  }
}
