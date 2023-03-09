package com.smart.catalog.controller;

import com.smart.catalog.domain.Teacher;
import com.smart.catalog.domain.TeacherOrder;
import com.smart.catalog.service.BookService;
import com.smart.catalog.service.TeacherOrderService;
import com.smart.catalog.service.TeacherService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDate;
import java.util.Collection;

@Controller
@RequestMapping("/teachers")
public class TeacherController {

    private final TeacherService teacherService;
    private final TeacherOrderService teacherOrderService;
    private final BookService bookService;

    public TeacherController(TeacherService teacherService, TeacherOrderService teacherOrderService, BookService bookService) {
        this.teacherService = teacherService;
        this.teacherOrderService = teacherOrderService;
        this.bookService = bookService;
    }

    @GetMapping
    private String getAll() {
        return "teachers";
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    private @ResponseBody Collection<Teacher> getTeachers() {
        return teacherService.getAll();
    }

    @PostMapping(path = "/save", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE )
    private ResponseEntity<Object> teacherSave(Teacher teacher) {

        teacherService.save(teacher);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping(path = "/orders/byid", produces = MediaType.APPLICATION_JSON_VALUE)
    private @ResponseBody Collection<TeacherOrder> getTeacherOrderByName(int id) {
        return teacherOrderService.getByTeacherId(id);
    }

    @GetMapping(path = "/orders/id", produces = MediaType.APPLICATION_JSON_VALUE)
    private @ResponseBody TeacherOrder getTeacherOrderById(int id) {
        return teacherOrderService.findById(id);
    }

    @PostMapping(path = "/orders/save", produces = MediaType.APPLICATION_JSON_VALUE)
    private ResponseEntity<Object> teacherOrderSave(int id,
                                  String book,
                                  String teacher,
                                  int quantity,
                                  int returned) {

        teacherOrderService.makeOrder(new TeacherOrder(id, bookService.findByName(book), LocalDate.now(), teacherService.findByName(teacher), quantity, returned));
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
