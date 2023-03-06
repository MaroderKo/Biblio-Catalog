package com.smart.catalog.controller;

import com.smart.catalog.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Optional;

@Controller
@RequestMapping("/books")
public class BookController {

    @GetMapping("")
    private String getAll(Model model) {
        //model.addAttribute("books", service.getAll());
        return "books";
    }

    @PostMapping("")
    private RedirectView searchStart(RedirectAttributes attributes,
                                     @RequestParam("page") Optional<Integer> page,
                                     @RequestParam("size") Optional<Integer> size,
                                     @RequestBody Optional<String> search)
    {
        page.ifPresent(p -> attributes.addAttribute("page", 1));
        page.ifPresent(s -> attributes.addAttribute("size", s));
        page.ifPresent(sear -> attributes.addAttribute("search", sear));
        return new RedirectView("redirectedUrl");
    }


}
