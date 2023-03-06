package com.smart.catalog.controller;

import com.smart.catalog.domain.Teacher;
import com.smart.catalog.service.TeacherOrderService;
import com.smart.catalog.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Comparator;
import java.util.List;

@Controller
@RequestMapping("/teachersformular")
public class TeacherFormularController {

    private final TeacherOrderService orderService;
    private final TeacherService teacherService;


    private TeacherFormularController(TeacherOrderService orderService, TeacherService teacherService) {
        this.orderService = orderService;
        this.teacherService = teacherService;
    }


    @GetMapping("")
    public String menu(Model model)
    {
        List<Teacher> teachers = teacherService.getAll();
        teachers.sort(Comparator.comparing(Teacher::getPib));
        model.addAttribute("teachers", teachers);
        model.addAttribute("orders", orderService.getAll());
        return "teachers-formular";
    }

}
