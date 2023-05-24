package com.codeup.codeupspringblog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PostController {
    @GetMapping("/posts")
    @ResponseBody
    public String index() {
        return "posts/";
    }
    @GetMapping("/posts/{id}")
    @ResponseBody
    public String showPost(@PathVariable int id) {
        return "posts/show";
    }
    @GetMapping("/posts/create")
    @ResponseBody
    public String createPostForm() {
        return "posts/create";
    }
    @PostMapping("/posts/create")
    @ResponseBody
    public String createPost() {
        return "posts/create";
    }
}
