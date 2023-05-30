package com.codeup.codeupspringblog.controllers;

import com.codeup.codeupspringblog.post.Post;
import com.codeup.codeupspringblog.post.PostRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Controller
public class PostController {
    private final PostRepository postDao;

    public PostController(PostRepository postDao) {
        this.postDao = postDao;
    }

    @GetMapping("/posts/index")
    public String index(Model model) {
        model.addAttribute("posts", postDao.findAll());
        return "posts/index";
    }
    @GetMapping("/posts/show/{id}")
    public String showPost(@PathVariable int id, Model model) {
        Post post = new Post("Test", "This is a test post using a path variable");
        model.addAttribute("post", post);
        return "posts/show";
    }

    @GetMapping("/posts/create")
    public String createPostForm() {
        return "posts/create";
    }
    @PostMapping("/posts/create")
    public String createPost(@ModelAttribute("post") Post post, Model model) {
        postDao.save(post);
        return "redirect:index";
    }
}
