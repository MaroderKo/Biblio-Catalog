package com.smart.catalog.Service;

import com.smart.catalog.Domain.Book;
import com.smart.catalog.Domain.BookType;
import com.smart.catalog.Repository.BookRepository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService extends AbstractService<Book, BookRepository>{


    public BookService(BookRepository repository) {
        super(repository);
    }

    public List<Book> findByType(BookType type)
    {
        return repository.findBooksByTypeEquals(type);
    }

    public Book findByName(String name)
    {
        return repository.findBookByName(name);
    }
    @Cacheable("books")
    public List<String> getNames()
    {
        return repository.getBooksNames();
    }

    @Override
    @CacheEvict(value="books", allEntries=true)
    public Book save(Book book) {
        return super.save(book);
    }

    @Override
    @CacheEvict(value="books", allEntries=true)
    public boolean delete(Book book) {
        return super.delete(book);
    }
}
