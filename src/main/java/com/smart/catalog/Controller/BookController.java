package com.smart.catalog.Controller;

import com.smart.catalog.Domain.Book;
import com.smart.catalog.Service.BookService;
import com.smart.catalog.Util.ViewUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequestMapping("/books")
public class BookController {
    @Autowired
    BookService service;

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
