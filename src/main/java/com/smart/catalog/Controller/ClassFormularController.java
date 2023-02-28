package com.smart.catalog.Controller;

import com.smart.catalog.Domain.SchoolClass;
import com.smart.catalog.Service.ClassOrderService;
import com.smart.catalog.Service.SchoolClassService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Comparator;
import java.util.List;

@Controller
@RequestMapping("/classesformular")
public class ClassFormularController {

    final ClassOrderService orderService;
    final SchoolClassService schoolClassService;

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
