package com.smart.catalog.controller;

import com.smart.catalog.dto.BookDTO;
import com.smart.catalog.domain.*;
import com.smart.catalog.exception.SearchItemsNotFoundException;
import com.smart.catalog.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/ajax")
public class AjaxController {

    private static final Logger LOG = LoggerFactory.getLogger(AjaxController.class);

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
    private Collection<BookDTO> getAllFictionBook() {
        List<Book> books = bookService.findByType(BookType.FICTION_BOOK);
        List<BookDTO> result = new ArrayList<>();
        for(Book book : books)
        {
            BookDTO dto = new BookDTO(book);
            dto.setBalance(dto.getQuantity());
            studentOrderService.getByBookId(dto.getId()).forEach(o -> dto.setBalance(dto.getBalance()-o.getQuantity()+o.getReturned()));
            teacherOrderService.getByBookId(dto.getId()).forEach(o -> dto.setBalance(dto.getBalance()-o.getQuantity()+o.getReturned()));
            result.add(dto);
        }
        return result;
    }

    @GetMapping("/books/school")
    private Collection<BookDTO> getAllSchoolBook() {
        List<Book> books = bookService.findByType(BookType.SCHOOL_BOOK);
        List<BookDTO> result = new ArrayList<>();
        for(Book book : books)
        {
            BookDTO dto = new BookDTO(book);
            dto.setBalance(dto.getQuantity());
            studentOrderService.getByBookId(dto.getId()).forEach(o -> dto.setBalance(dto.getBalance()-o.getQuantity()+o.getReturned()));
            teacherOrderService.getByBookId(dto.getId()).forEach(o -> dto.setBalance(dto.getBalance()-o.getQuantity()+o.getReturned()));
            result.add(dto);
        }
        return result;
    }

    @GetMapping("/books/names")
    private Collection<String> getAllBookNames() {
        return bookService.getNames();
    }

    @PostMapping(path = "/books/writeoff", produces = MediaType.APPLICATION_JSON_VALUE)
    private void bookWriteOff( @RequestParam("name") String name, @RequestParam("quantity") int quantity) {
        Book byName = bookService.findByName(name);
        if (byName == null)
        {
            throw new SearchItemsNotFoundException();
        }
        byName.setQuantity(byName.getQuantity()-quantity);
        bookService.save(byName);
        LOG.info("Була списана книга \""+name+"\" в кількості "+quantity+" автора "+byName.getAuthor());
    }
    @PostMapping(path = "/books/earning", produces = MediaType.APPLICATION_JSON_VALUE)
    private void bookEarning( @RequestParam("name") String name, @RequestParam("quantity") int quantity) {
        Book byName = bookService.findByName(name);
        if (byName == null)
        {
            throw new SearchItemsNotFoundException();
        }
        byName.setQuantity(byName.getQuantity()+quantity);
        bookService.save(byName);
        LOG.info("Надійшла книга \""+name+"\" в кількості "+quantity+" автора "+byName.getAuthor());
    }

    @PostMapping(path = "/books/save", produces = MediaType.APPLICATION_JSON_VALUE)
    private void bookSave(@RequestParam("id") int id, @RequestParam("name") String name, @RequestParam("publisher") String publisher, @RequestParam("author") String author, @RequestParam("type") String type, @RequestParam("year") int year, @RequestParam("size") int size, @RequestParam("quantity") int quantity) {
        Book byName = bookService.findByName(name);
        if (byName != null)
        {
            LOG.info("Надійшло "+quantity+" книг(и) з назвою \""+name+"\" автора "+author+" в кількості "+quantity);
            byName.setQuantity(byName.getQuantity()+quantity);
            bookService.save(byName);
            return;
        }
        Book book = new Book();
        book.setId(id);
        book.setAuthor(author);
        book.setName(name);
        book.setPublisher(publisher);
        book.setPublishYear(year);
        book.setSize(size);
        book.setQuantity(quantity);
        book.setType(type.equals("Підручник") ? BookType.SCHOOL_BOOK : BookType.FICTION_BOOK);
        bookService.save(book);
        LOG.info("Була додана книга \""+name+"\" в кількості "+quantity+" автора "+author);
    }

    //CLASS
    @GetMapping(path = "/schoolclasses/names", produces = MediaType.APPLICATION_JSON_VALUE)
    private Collection<String> getClassNames() {
        return schoolClassService.getAll().stream().map(SchoolClass::getName).collect(Collectors.toList());
    }

    @PostMapping(path = "/schoolclasses/save", produces = MediaType.APPLICATION_JSON_VALUE)
    private void classSave(@RequestParam("id") int id,
                           @RequestParam("name") String name,
                           @RequestParam("teacher") String teacher) {
        System.out.println(name);
        SchoolClass schoolClass = new SchoolClass();
        schoolClass.setId(id);
        schoolClass.setName(name);
        schoolClass.setClassTeacher(teacher);
        schoolClassService.save(schoolClass);
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
        if (id == 0)
        {
            LOG.info("Збережено нового учня -> "+pib);
        }
        else
        {
            LOG.info("Змінено учня -> "+pib);
            LOG.info("Попередні дані:"+bookService.getById(id));
        }
        Student student = new Student();
        student.setId(id);
        student.setPib(pib);
        student.setPhone(phone);
        student.setBirthDate(birth_date);
        student.setAddress(address);
        student.setSchoolclass(schoolClassService.getByName(class_name));
        studentService.save(student);
        if (id != 0)
            LOG.info("Нові дані -> "+student);
    }

    //STUDENTS_ORDER
    @PostMapping(path = "/studentorders/save", produces = MediaType.APPLICATION_JSON_VALUE)
    private void studentOrderSave(@RequestParam("id") int id, @RequestParam("book") String book, @RequestParam("student") String student, @RequestParam("quantity") int quantity, @RequestParam("returned") int returned) {

        if (id == 0 ) {
            returned = 0;
            LOG.info("Учень "+student+" взяв книгу "+book+" в кількості "+quantity);
        }
        if (returned >= quantity)
        {
            LOG.info("Учень "+student+" повернув книгу "+book+" в кількості "+quantity);
            studentOrderService.delete(studentOrderService.getById(id));
            return;
        }
        StudentOrder order = new StudentOrder();
        order.setId(id);
        order.setStudent(studentService.getAll().stream().filter(student1 -> student1.getPib().equals(student)).findAny().orElse(null));
        if (order.getStudent() == null)
            throw new SearchItemsNotFoundException();
        order.setBook(bookService.findByName(book));
        if (order.getBook() == null)
            throw new SearchItemsNotFoundException();
        order.setQuantity(quantity);
        order.setReturned(returned);
        studentOrderService.save(order);
        if (id == 0)
            LOG.info("Учень "+student+" взяв книгу "+book+" в кількості "+quantity);
    }

    //TEACHER

    @GetMapping(path = "/teachers", produces = MediaType.APPLICATION_JSON_VALUE)
    private Collection<Teacher> getTeachers() {
        return teacherService.getAll();
    }

    @PostMapping(path = "/teachers/save", produces = MediaType.APPLICATION_JSON_VALUE)
    private void teacherSave(@RequestParam("id") int id, @RequestParam("pib") String pib, @RequestParam("phone") String phone, @RequestParam("birth_date") int birth_date, @RequestParam("profession") String profession, @RequestParam("address") String address) {

        Teacher teacher = new Teacher();
        teacher.setId(id);
        teacher.setPib(pib);
        teacher.setPhone(phone);
        teacher.setBirthDate(birth_date);
        teacher.setAddress(address);
        teacher.setProfession(profession);
        teacherService.save(teacher);
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

        if (id == 0 ) {
            returned = 0;
            LOG.info("Викладач "+teacher+" взяв книгу "+book);
        }
        if (returned >= quantity)
        {
            LOG.info("Викладач "+teacher+" повернув книгу "+book);
            teacherOrderService.delete(teacherOrderService.getById(id));
            return;
        }
        TeacherOrder order = new TeacherOrder();
        order.setId(id);
        order.setTeacher(teacherService.getAll().stream().filter(teacher1 -> teacher1.getPib().equals(teacher)).findAny().orElse(null));
        if (order.getTeacher() == null)
            throw new SearchItemsNotFoundException();
        order.setBook(bookService.findByName(book));
        if (order.getBook() == null)
            throw new SearchItemsNotFoundException();
        order.setQuantity(quantity);
        order.setReturned(returned);
        teacherOrderService.save(order);
        if (id == 0)
            LOG.info("Викладач "+teacher+" взяв книгу "+book);
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

        if (id == 0 ) {
            returned = 0;
            LOG.info("На клас "+Class+" записано книгу "+book+" в кількості "+quantity);
        }
        if (returned >= quantity)
        {
            LOG.info("Клас "+Class+" здав книгу "+book+" в кількості "+quantity);
            classOrderService.delete(classOrderService.getById(id));
            return;
        }
        ClassOrder order = new ClassOrder();
        order.setId(id);
        order.setSchoolClass(schoolClassService.getAll().stream().filter(class1 -> class1.getName().equals(Class)).findAny().orElse(null));
        if (order.getSchoolClass() == null)
            throw new SearchItemsNotFoundException();
        order.setBook(bookService.findByName(book));
        if (order.getBook() == null)
            throw new SearchItemsNotFoundException();
        order.setQuantity(quantity);
        order.setReturned(returned);
        classOrderService.save(order);
        if (id == 0)
            LOG.info("На клас "+Class+" записано книгу "+book+" в кількості "+quantity);
    }

}
