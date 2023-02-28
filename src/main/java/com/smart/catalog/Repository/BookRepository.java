package com.smart.catalog.Repository;

import com.smart.catalog.Domain.Book;
import com.smart.catalog.Domain.BookType;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface BookRepository extends AbstractRepository<Book>{
    public List<Book> findBooksByTypeEquals(BookType type);

    public Book findBookByName(String name);

    @Query("select b.name from Book b")
    public List<String> getBooksNames();
}
