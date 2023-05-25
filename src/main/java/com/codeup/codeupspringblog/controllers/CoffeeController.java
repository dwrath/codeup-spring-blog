package com.codeup.codeupspringblog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class CoffeeController {
    @GetMapping("/coffee")
    public String coffeePage() {
        return "coffee";
    }

    @GetMapping("/coffee/{roast}")
    public String favRoast(@PathVariable String roast, Model model) {
        model.addAttribute("roast", roast);
        return "coffee";
    }
}
