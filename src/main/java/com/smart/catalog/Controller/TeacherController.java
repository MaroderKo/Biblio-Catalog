package com.smart.catalog.Controller;

import com.smart.catalog.Domain.SchoolClass;
import com.smart.catalog.Service.SchoolClassService;
import com.smart.catalog.Service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Comparator;
import java.util.List;

@Controller
@RequestMapping("/teachers")
public class TeacherController {

    @GetMapping("")
    private String getAll(Model model) {
        //model.addAttribute("students", service.getAll());
//        List<SchoolClass> classes = service.getAll();
//        classes.sort(Comparator.comparing(SchoolClass::getName));
//        model.addAttribute("classes", classes);
        return "teachers";
    }


}
