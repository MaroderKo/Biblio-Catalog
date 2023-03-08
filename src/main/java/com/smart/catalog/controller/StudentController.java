package com.smart.catalog.controller;

import com.smart.catalog.service.SchoolClassService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/students")
public class StudentController {

    private final SchoolClassService service;


    public StudentController(SchoolClassService service) {
        this.service = service;
    }

    @GetMapping("")
    private String getAll(Model model) {
        model.addAttribute("classes", service.getAllSortedByName());
        return "students";
    }


}
