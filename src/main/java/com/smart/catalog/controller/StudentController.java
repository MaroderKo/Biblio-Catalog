package com.smart.catalog.controller;

import com.smart.catalog.domain.SchoolClass;
import com.smart.catalog.service.SchoolClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;

@Controller
@RequestMapping("/students")
public class StudentController {

    private final SchoolClassService service;


    private StudentController(SchoolClassService service) {
        this.service = service;
    }

    @GetMapping("")
    private String getAll(Model model) {
        //model.addAttribute("students", service.getAll());
        List<SchoolClass> classes = service.getAll();
        classes.sort(Comparator.comparing(SchoolClass::getName));
        model.addAttribute("classes", classes);
        return "students";
    }


}
