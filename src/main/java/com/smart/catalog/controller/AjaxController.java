package com.smart.catalog.controller;

import com.smart.catalog.domain.*;
import com.smart.catalog.dto.BookTransferDTO;
import com.smart.catalog.service.*;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/ajax")
public class AjaxController {

    private final BookService bookService;
    private final StudentService studentService;
    private final TeacherService teacherService;
    private final TeacherOrderService teacherOrderService;
    private final SchoolClassService schoolClassService;
    private final StudentOrderService studentOrderService;
    private final ClassOrderService classOrderService;

    public AjaxController(BookService bookService, StudentService studentService, TeacherService teacherService, TeacherOrderService teacherOrderService, SchoolClassService schoolClassService, StudentOrderService studentOrderService, ClassOrderService classOrderService) {
        this.bookService = bookService;
        this.studentService = studentService;
        this.teacherService = teacherService;
        this.teacherOrderService = teacherOrderService;
        this.schoolClassService = schoolClassService;
        this.studentOrderService = studentOrderService;
        this.classOrderService = classOrderService;
    }

    //BOOK
    @GetMapping("/books/fiction")
    private List<BookTransferDTO> getAllFictionBook() {
        return bookService.getAllFictionBook();
    }

    @GetMapping("/books/school")
    private Collection<BookTransferDTO> getAllSchoolBook() {
        return bookService.getAllSchoolBook();
    }

    @GetMapping("/books/names")
    private Collection<String> getAllBookNames() {
        return bookService.getNames();
    }

    @PostMapping(path = "/books/writeoff", produces = MediaType.APPLICATION_JSON_VALUE)
    private void bookWriteOff( @RequestParam("name") String name, @RequestParam("quantity") int quantity) {
        Book book = bookService.changeQuantityByName(name, -quantity);

    }
    @PostMapping(path = "/books/earning", produces = MediaType.APPLICATION_JSON_VALUE)
    private void bookEarning( @RequestParam("name") String name, @RequestParam("quantity") int quantity) {
        Book book = bookService.changeQuantityByName(name, quantity);

    }

    @PostMapping(path = "/books/save", produces = MediaType.APPLICATION_JSON_VALUE)
    private void bookSave(@RequestParam("name") String name, @RequestParam("publisher") String publisher, @RequestParam("author") String author, @RequestParam("type") String type, @RequestParam("year") int year, @RequestParam("size") int size, @RequestParam("quantity") int quantity) {
        bookService.saveNew(new Book(name,year,author,publisher,type.equals("Підручник") ? BookType.SCHOOL_BOOK : BookType.FICTION_BOOK,size,quantity));

    }

    //CLASS
    @GetMapping(path = "/schoolclasses/names", produces = MediaType.APPLICATION_JSON_VALUE)
    private Collection<String> getClassNames() {
        return schoolClassService.getClassNames();
    }

    @PostMapping(path = "/schoolclasses/save", produces = MediaType.APPLICATION_JSON_VALUE)
    private void classSave(@RequestParam("id") int id,
                           @RequestParam("name") String name,
                           @RequestParam("teacher") String teacher) {
        schoolClassService.save(new SchoolClass(id,name,List.of(),teacher));
    }

    //STUDENTS
    @GetMapping(path = "/studentorders/byid", produces = MediaType.APPLICATION_JSON_VALUE)
    private Collection<StudentOrder> getStudentOrderByName(@RequestParam("id") int id) {
        return studentOrderService.getByStudentId(id);
    }

    @GetMapping(path = "/studentorders/id", produces = MediaType.APPLICATION_JSON_VALUE)
    private StudentOrder getStudentOrderById(@RequestParam("id") int id) {
        return studentOrderService.findById(id);
    }

    @PostMapping(path = "/students/save", produces = MediaType.APPLICATION_JSON_VALUE)
    private void studentSave(@RequestParam("id") int id, @RequestParam("pib") String pib, @RequestParam("phone") String phone, @RequestParam("birth_date") int birth_date, @RequestParam("address") String address, @RequestParam("class-name") String class_name) {

        studentService.save(new Student(id,pib,birth_date,phone,address,schoolClassService.getByName(class_name)));
    }

    //STUDENTS_ORDER
    @PostMapping(path = "/studentorders/save", produces = MediaType.APPLICATION_JSON_VALUE)
    private void studentOrderSave(@RequestParam("id") int id, @RequestParam("book") String book, @RequestParam("student") String student, @RequestParam("quantity") int quantity, @RequestParam("returned") int returned) {

        studentOrderService.saveOrder(id,bookService.getByName(book),studentService.getByName(student),quantity,returned);
    }

    //TEACHER

    @GetMapping(path = "/teachers", produces = MediaType.APPLICATION_JSON_VALUE)
    private Collection<Teacher> getTeachers() {
        return teacherService.getAll();
    }

    @PostMapping(path = "/teachers/save", produces = MediaType.APPLICATION_JSON_VALUE)
    private void teacherSave(@RequestParam("id") int id, @RequestParam("pib") String pib, @RequestParam("phone") String phone, @RequestParam("birth_date") int birth_date, @RequestParam("profession") String profession, @RequestParam("address") String address) {

        teacherService.save(new Teacher(id,pib,birth_date,phone,address,profession));
    }
    //TEACHER_ORDER
    @GetMapping(path = "/teacherorders/byid", produces = MediaType.APPLICATION_JSON_VALUE)
    private Collection<TeacherOrder> getTeacherOrderByName(@RequestParam("id") int id) {
        return teacherOrderService.getByTeacherId(id);
    }

    @GetMapping(path = "/teacherorders/id", produces = MediaType.APPLICATION_JSON_VALUE)
    private TeacherOrder getTeacherOrderById(@RequestParam("id") int id) {
        return teacherOrderService.findById(id);
    }
    @PostMapping(path = "/teacherorders/save", produces = MediaType.APPLICATION_JSON_VALUE)
    private void teacherOrderSave(@RequestParam("id") int id,
                                  @RequestParam("book") String book,
                                  @RequestParam("teacher") String teacher,
                                  @RequestParam("quantity") int quantity,
                                  @RequestParam("returned") int returned) {

        teacherOrderService.makeOrder(new TeacherOrder(id,bookService.findByName(book), LocalDate.now(),teacherService.findByName(teacher),quantity,returned));

    }
    
    //ClassOrder
    @GetMapping(path = "/classorders/byid", produces = MediaType.APPLICATION_JSON_VALUE)
    private Collection<ClassOrder> getClassOrderByName(@RequestParam("id") int id) {
        return classOrderService.getByClassId(id);
    }

    @GetMapping(path = "/classorders/id", produces = MediaType.APPLICATION_JSON_VALUE)
    private ClassOrder getClassOrderById(@RequestParam("id") int id) {
        return classOrderService.getById(id);
    }
    @PostMapping(path = "/classorders/save", produces = MediaType.APPLICATION_JSON_VALUE)
    private void classOrderSave(@RequestParam("id") int id,
                                  @RequestParam("book") String book,
                                  @RequestParam("schoolclass") String Class,
                                  @RequestParam("quantity") int quantity,
                                  @RequestParam("returned") int returned) {


        ClassOrder order = new ClassOrder(id,schoolClassService.getByName(Class),LocalDate.now(),bookService.findByName(book),quantity,returned);
        classOrderService.makeOrder(order);
    }

}
