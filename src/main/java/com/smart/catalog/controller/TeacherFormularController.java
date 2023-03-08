package com.smart.catalog.controller;

import com.smart.catalog.service.TeacherOrderService;
import com.smart.catalog.service.TeacherService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/teachersformular")
public class TeacherFormularController {

    private final TeacherOrderService orderService;
    private final TeacherService teacherService;


    public TeacherFormularController(TeacherOrderService orderService, TeacherService teacherService) {
        this.orderService = orderService;
        this.teacherService = teacherService;
    }


    @GetMapping("")
    public String menu(Model model)
    {
        model.addAttribute("teachers", teacherService.getAllSortedByName());
        model.addAttribute("orders", orderService.getAll());
        return "teachers-formular";
    }

}
