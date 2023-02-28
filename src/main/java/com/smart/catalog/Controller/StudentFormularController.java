package com.smart.catalog.Controller;

import com.smart.catalog.Domain.SchoolClass;
import com.smart.catalog.Service.SchoolClassService;
import com.smart.catalog.Service.StudentOrderService;
import com.smart.catalog.Service.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Comparator;
import java.util.List;

@Controller
@RequestMapping("/studentsformular")
public class StudentFormularController {

    final StudentOrderService orderService;
    final SchoolClassService schoolClassService;

    public StudentFormularController(StudentOrderService orderService, SchoolClassService schoolClassService) {
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
