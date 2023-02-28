package com.smart.catalog.Controller;

import com.smart.catalog.Domain.SchoolClass;
import com.smart.catalog.Domain.Teacher;
import com.smart.catalog.Service.TeacherOrderService;
import com.smart.catalog.Service.TeacherService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Comparator;
import java.util.List;

@Controller
@RequestMapping("/teachersformular")
public class TeacherFormularController {

    final TeacherOrderService orderService;
    final TeacherService teacherService;

    public TeacherFormularController(TeacherOrderService orderService, TeacherService teacherService) {
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
