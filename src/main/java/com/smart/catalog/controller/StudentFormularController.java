package com.smart.catalog.controller;

import com.smart.catalog.service.SchoolClassService;
import com.smart.catalog.service.StudentOrderService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/studentsformular")
public class StudentFormularController {

    private final StudentOrderService orderService;
    private final SchoolClassService schoolClassService;


    public StudentFormularController(StudentOrderService orderService, SchoolClassService schoolClassService) {
        this.orderService = orderService;
        this.schoolClassService = schoolClassService;
    }

    @GetMapping("")
    public String menu(Model model)
    {
        model.addAttribute("classes", schoolClassService.getAllSortedByName());
        model.addAttribute("orders", orderService.getAll());
        return "students-formular";
    }

}
