package com.smart.catalog.controller;

import com.smart.catalog.domain.SchoolClass;
import com.smart.catalog.service.SchoolClassService;
import com.smart.catalog.service.StudentOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Comparator;
import java.util.List;

@Controller
@RequestMapping("/studentsformular")
public class StudentFormularController {

    private final StudentOrderService orderService;
    private final SchoolClassService schoolClassService;


    private StudentFormularController(StudentOrderService orderService, SchoolClassService schoolClassService) {
        this.orderService = orderService;
        this.schoolClassService = schoolClassService;
    }

    @GetMapping("")
    public String menu(Model model)
    {
        List<SchoolClass> classes = schoolClassService.getAll();
        classes.sort(Comparator.comparing(SchoolClass::getName));
        model.addAttribute("classes", classes);
        model.addAttribute("orders", orderService.getAll());
        return "students-formular";
    }

}
