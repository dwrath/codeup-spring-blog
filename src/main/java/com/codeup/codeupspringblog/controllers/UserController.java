package com.codeup.codeupspringblog.controllers;

import com.codeup.codeupspringblog.models.User;
import com.codeup.codeupspringblog.repositories.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {
    private final UserRepository usersDao;
    private final PasswordEncoder passwordEncoder;

    public UserController(UserRepository usersDao, PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
        this.usersDao = usersDao;
    }

    @GetMapping("/login")
    public String showLoginForm() {
        return "/login";
    }

    @GetMapping("/register")
    public String register() {
        return "/register";
    }

    @PostMapping("/register")
    public String registerUser(@RequestParam(name = "username") String username, @RequestParam(name = "email") String email, @RequestParam(name = "password") String password) {
        password = passwordEncoder.encode(password);
        usersDao.save(new User(username, email, password));
        return "redirect:/posts/create";
    }

    @GetMapping("/posts/user/{id}/ads")
    public String userAds(@PathVariable long id, Model model) {
        User user = usersDao.findUserById(id);
        model.addAttribute("userAds", user.getPosts());
        return "posts/my_posts";
    }
    @GetMapping("posts/profile")
    public String profile(Model model) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("user", user);
        return "posts/profile";
    }
    @PostMapping("posts/profile")
    public String updateEmail(@RequestParam(name = "email") String email) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        long id = user.getId();
        user = usersDao.findUserById(id);
        user.setEmail(email);
        usersDao.save(user);
        return "redirect:/posts/profile";
    }
}

