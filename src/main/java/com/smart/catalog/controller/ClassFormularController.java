package com.smart.catalog.controller;

import com.smart.catalog.service.ClassOrderService;
import com.smart.catalog.service.SchoolClassService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/class/formular")
public class ClassFormularController {

    private final ClassOrderService orderService;
    private final SchoolClassService schoolClassService;


    public ClassFormularController(ClassOrderService orderService, SchoolClassService schoolClassService) {
        this.orderService = orderService;
        this.schoolClassService = schoolClassService;
    }


    @GetMapping("")
    public String menu(Model model)
    {
        model.addAttribute("schoolclasses", schoolClassService.getAllSortedByName());
        model.addAttribute("orders", orderService.getAll());
        return "classes-formular";
    }

}
