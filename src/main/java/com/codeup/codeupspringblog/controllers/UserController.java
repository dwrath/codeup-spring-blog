package com.codeup.codeupspringblog.controllers;

import com.codeup.codeupspringblog.models.User;
import com.codeup.codeupspringblog.repositories.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
@Controller
public class UserController {
        private final UserRepository usersDao;

        public UserController(UserRepository usersDao){
            this.usersDao = usersDao;
        }

        @GetMapping("/posts/register")
        public String register(){
            return "posts/register";
        }

        @PostMapping("posts/register")
        public String registerUser(@RequestParam(name="username") String username, @RequestParam(name="email") String email, @RequestParam(name="password") String password){
            User user = new User(username, email, password);
            usersDao.save(user);
            return "redirect:index";
        }

        @GetMapping("/posts/user/{id}/ads")
        public String userAds(@PathVariable long id, Model model){
            User user = usersDao.findUserById(id);
            model.addAttribute("userAds", user.getPosts());
            return "posts/my_posts";
        }
    }

