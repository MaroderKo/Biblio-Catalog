package com.smart.catalog.controller;

import com.smart.catalog.domain.Student;
import com.smart.catalog.domain.StudentOrder;
import com.smart.catalog.service.BookService;
import com.smart.catalog.service.SchoolClassService;
import com.smart.catalog.service.StudentOrderService;
import com.smart.catalog.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@Controller
@RequestMapping("/students")
public class StudentController {

    private final BookService bookService;
    private final StudentService studentService;
    private final SchoolClassService schoolClassService;
    private final StudentOrderService studentOrderService;

    public StudentController(BookService bookService, StudentService studentService, SchoolClassService schoolClassService, StudentOrderService studentOrderService) {
        this.bookService = bookService;
        this.studentService = studentService;
        this.schoolClassService = schoolClassService;
        this.studentOrderService = studentOrderService;
    }

    @GetMapping("")
    private String getAll(Model model) {
        model.addAttribute("classes", schoolClassService.getAllSortedByName());
        return "students";
    }

    @GetMapping("/formular")
    public String menu(Model model)
    {
        model.addAttribute("classes", schoolClassService.getAllSortedByName());
        model.addAttribute("orders", studentOrderService.getAll());
        return "students-formular";
    }

    @GetMapping(path = "/orders/byid", produces = MediaType.APPLICATION_JSON_VALUE)
    private @ResponseBody Collection<StudentOrder> getStudentOrderByName(int id) {
        return studentOrderService.getByStudentId(id);
    }

    @GetMapping(path = "/orders/id", produces = MediaType.APPLICATION_JSON_VALUE)
    private @ResponseBody
    StudentOrder getStudentOrderById(int id) {
        return studentOrderService.findById(id);
    }

    @PostMapping(path = "/save", produces = MediaType.APPLICATION_JSON_VALUE)
    private ResponseEntity<Object> studentSave(int id,
                                       String pib,
                                       String phone,
                                       int birth_date,
                                       String address,
                                       @RequestParam("class-name") String class_name) {

        studentService.save(new Student(id, pib, birth_date, phone, address, schoolClassService.getByName(class_name)));
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping(path = "/orders/save", produces = MediaType.APPLICATION_JSON_VALUE)
    private ResponseEntity<Object> studentOrderSave(int id,
                                  String book,
                                  String student,
                                  int quantity,
                                  int returned) {

        studentOrderService.saveOrder(id, bookService.getByName(book), studentService.getByName(student), quantity, returned);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }


}
