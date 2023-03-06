package com.smart.catalog.controller;

import com.smart.catalog.domain.SchoolClass;
import com.smart.catalog.service.ClassOrderService;
import com.smart.catalog.service.SchoolClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Comparator;
import java.util.List;

@Controller
@RequestMapping("/classesformular")
public class ClassFormularController {

    private final ClassOrderService orderService;
    private final SchoolClassService schoolClassService;

    @Autowired
    public ClassFormularController(ClassOrderService orderService, SchoolClassService schoolClassService) {
        this.orderService = orderService;
        this.schoolClassService = schoolClassService;
    }


    @GetMapping("")
    public String menu(Model model)
    {
        List<SchoolClass> classes = schoolClassService.getAll();
        classes.sort(Comparator.comparing(SchoolClass::getName));
        System.out.println(classes);
        model.addAttribute("schoolclasses", classes);
        model.addAttribute("orders", orderService.getAll());
        return "classes-formular";
    }

}
