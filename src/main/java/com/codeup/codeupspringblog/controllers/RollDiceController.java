package com.codeup.codeupspringblog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RollDiceController {
    @GetMapping("/roll-dice")
    public String rollDice() {
        return "roll-dice";
    }
    @GetMapping("/roll-dice/{n}")
    public String rollDiceGuess(@PathVariable int n, Model model, Model model2) {
        model.addAttribute("n", n);
        int random = (int) Math.floor(Math.random() * 6 + 1);
        model2.addAttribute("random", random);
        return "roll-dice";
    }

}
