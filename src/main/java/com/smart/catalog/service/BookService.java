package com.smart.catalog.service;

import com.smart.catalog.domain.Book;
import com.smart.catalog.domain.BookType;
import com.smart.catalog.dto.BookMapperDTO;
import com.smart.catalog.dto.BookTransferDTO;
import com.smart.catalog.exception.ItemAlreadyExistException;
import com.smart.catalog.repository.BookRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookService {

    private static final Logger LOG = LoggerFactory.getLogger(BookService.class);

    private final BookRepository repository;
    private final TeacherOrderService teacherOrderService;
    private final StudentOrderService studentOrderService;
    private final ClassOrderService classOrderService;


    public BookService(BookRepository repository, TeacherOrderService teacherOrderService, StudentOrderService studentOrderService, ClassOrderService classOrderService) {
        this.repository = repository;
        this.teacherOrderService = teacherOrderService;
        this.studentOrderService = studentOrderService;
        this.classOrderService = classOrderService;
    }

    public List<Book> findByType(BookType type) {
        return repository.findBooksByTypeEquals(type);
    }

    public Book findByName(String name) {
        return repository.findBookByName(name);
    }

    @Cacheable("books")
    public List<String> getNames() {
        return repository.findAllByIdGreaterThan(0)
                .stream()
                .map(BookMapperDTO::getName)
                .collect(Collectors.toUnmodifiableList());
    }


    @CacheEvict(value = "books", allEntries = true)
    public Book save(Book book) {
        return repository.save(book);
    }


    @CacheEvict(value = "books", allEntries = true)
    public void delete(Book book) {
        repository.delete(book);
    }

    public void changeQuantityByName(String name, int amount)
    {
        Book book = findByName(name);
        book.setQuantity(book.getQuantity() + amount);
        repository.save(book);
        if (amount < 0)
            LOG.info("Була списана книга \""+name+"\" в кількості "+amount+" автора "+book.getAuthor());
        else
            LOG.info("Надійшла книга \""+name+"\" в кількості "+amount+" автора "+book.getAuthor());
    }

    public void saveNew(Book book) {
        Book byName = findByName(book.getName());
        if (byName == null) {
            save(book);
            LOG.info("Була додана книга \""+book.getName()+"\" в кількості "+book.getQuantity()+" автора "+book.getAuthor());
        }
        else
        {
            if (book.equals(byName))
            {
                throw new ItemAlreadyExistException();
            }
            else {
                book.setQuantity(byName.getQuantity());
                save(book);
            }
        }
        LOG.info("Була змінена книга \""+book.getName()+"\" в кількості "+book.getQuantity()+" автора "+book.getAuthor());
    }

    public Book getByName(String book) {
        return findByName(book);
    }

    public List<BookTransferDTO> getAllSchoolBook()
    {
        return findByType(BookType.SCHOOL_BOOK).stream()
                .map( b -> new BookTransferDTO(b, studentOrderService,teacherOrderService,classOrderService))
                .collect(Collectors.toUnmodifiableList());
    }
    public List<BookTransferDTO> getAllFictionBook()
    {
        return findByType(BookType.FICTION_BOOK).stream()
                .map( b -> new BookTransferDTO(b, studentOrderService,teacherOrderService,classOrderService))
                .collect(Collectors.toUnmodifiableList());
    }
}
