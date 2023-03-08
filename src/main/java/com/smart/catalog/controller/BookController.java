package com.smart.catalog.controller;

import com.smart.catalog.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Optional;
import java.util.OptionalInt;

@Controller
@RequestMapping("/books")
public class BookController {

    @GetMapping("")
    private String getAll() {
        return "books";
    }


}
