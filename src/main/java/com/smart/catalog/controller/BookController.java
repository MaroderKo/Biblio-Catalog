package com.smart.catalog.controller;

import com.smart.catalog.domain.Book;
import com.smart.catalog.domain.BookType;
import com.smart.catalog.dto.BookTransferDTO;
import com.smart.catalog.service.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@Controller
@RequestMapping("/books")
public class BookController {

    BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("")
    private String getAll() {
        return "books";
    }

    @GetMapping("/fiction")
    private
    @ResponseBody List<BookTransferDTO> getAllFictionBook() {
        return bookService.getAllFictionBook();
    }

    @GetMapping("/school")
    private @ResponseBody Collection<BookTransferDTO> getAllSchoolBook() {
        return bookService.getAllSchoolBook();
    }

    @GetMapping("/names")
    private @ResponseBody Collection<String> getAllBookNames() {
        return bookService.getNames();
    }

    @PostMapping(path = "/writeoff", produces = MediaType.APPLICATION_JSON_VALUE)
    private ResponseEntity<Object> bookWriteOff(String name,
                              int quantity) {
        bookService.changeQuantityByName(name, -quantity);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

    @PostMapping(path = "/earning", produces = MediaType.APPLICATION_JSON_VALUE)
    private ResponseEntity<Object> bookEarning(String name,
                                               int quantity) {
        bookService.changeQuantityByName(name, quantity);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

    @PostMapping(path = "/save", produces = MediaType.APPLICATION_JSON_VALUE)
    private ResponseEntity<Object> bookSave(String name,
                                  String publisher,
                                  String author,
                                  String type,
                                  int year,
                                  int size,
                                  int quantity) {
        bookService.saveNew(new Book(name, year, author, publisher, type.equals("Підручник") ? BookType.SCHOOL_BOOK : BookType.FICTION_BOOK, size, quantity));
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }


}
