package com.smart.catalog.Controller;

import com.smart.catalog.Domain.Book;
import com.smart.catalog.Domain.SchoolClass;
import com.smart.catalog.Domain.Student;
import com.smart.catalog.Service.BookService;
import com.smart.catalog.Service.SchoolClassService;
import com.smart.catalog.Util.ViewUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequestMapping("/students")
public class StudentController {
    @Autowired
    SchoolClassService service;

    @GetMapping("")
    private String getAll(Model model) {
        //model.addAttribute("students", service.getAll());
        List<SchoolClass> classes = service.getAll();
        classes.sort(Comparator.comparing(SchoolClass::getName));
        model.addAttribute("classes", classes);
        return "students";
    }


}
