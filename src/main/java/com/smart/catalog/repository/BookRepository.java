package com.smart.catalog.repository;

import com.smart.catalog.domain.Book;
import com.smart.catalog.domain.BookType;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface BookRepository extends AbstractRepository<Book>{
    List<Book> findBooksByTypeEquals(BookType type);

    Book findBookByName(String name);

    @Query("select b.name from Book b")
    List<String> getBooksNames();
}
