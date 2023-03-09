package com.smart.catalog.controller;

import com.smart.catalog.domain.ClassOrder;
import com.smart.catalog.domain.SchoolClass;
import com.smart.catalog.service.BookService;
import com.smart.catalog.service.ClassOrderService;
import com.smart.catalog.service.SchoolClassService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

@Controller
@RequestMapping("/class")
public class SchoolClassController {

    private final SchoolClassService schoolClassService;
    private final ClassOrderService classOrderService;
    private final BookService bookService;

    public SchoolClassController(SchoolClassService schoolClassService, ClassOrderService classOrderService, BookService bookService) {
        this.schoolClassService = schoolClassService;
        this.classOrderService = classOrderService;
        this.bookService = bookService;
    }

    @GetMapping(path = "/names", produces = MediaType.APPLICATION_JSON_VALUE)
    private @ResponseBody Collection<String> getClassNames() {
        return schoolClassService.getClassNames();
    }

    @PostMapping(path = "/save", produces = MediaType.APPLICATION_JSON_VALUE)
    private ResponseEntity<Object> classSave(int id,
                           String name,
                           String teacher) {
        schoolClassService.save(new SchoolClass(id, name, List.of(), teacher));
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    @GetMapping(path = "/orders/byid", produces = MediaType.APPLICATION_JSON_VALUE)
    private @ResponseBody Collection<ClassOrder> getClassOrderByName(int id) {
        return classOrderService.getByClassId(id);
    }

    @GetMapping(path = "/orders/id", produces = MediaType.APPLICATION_JSON_VALUE)
    private @ResponseBody ClassOrder getClassOrderById(int id) {
        return classOrderService.getById(id);
    }

    @PostMapping(path = "/orders/save", produces = MediaType.APPLICATION_JSON_VALUE)
    private ResponseEntity<Object> classOrderSave(int id,
                                String book,
                                @RequestParam("schoolclass") String Class,
                                int quantity,
                                int returned) {


        ClassOrder order = new ClassOrder(id, schoolClassService.getByName(Class), LocalDate.now(), bookService.findByName(book), quantity, returned);
        classOrderService.makeOrder(order);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
