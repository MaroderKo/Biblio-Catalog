package com.smart.catalog.repository;

import com.smart.catalog.domain.Book;
import com.smart.catalog.domain.BookType;
import com.smart.catalog.dto.BookMapperDTO;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface BookRepository extends AbstractRepository<Book>{
    List<Book> findBooksByTypeEquals(BookType type);

    Book findBookByName(String name);


    List<BookMapperDTO> findAllByIdGreaterThan(int id);
}
