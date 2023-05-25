package com.codeup.codeupspringblog.controllers;

import com.codeup.codeupspringblog.post.Post;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;

@Controller
public class PostController {
    @GetMapping("/posts/index")
    public String index(Model model) {
        ArrayList<Post> posts = new ArrayList<>();
        posts.add(new Post("Test", "This is a test post"));
        posts.add(new Post("Test 2", "This is a test post 2"));
        model.addAttribute("posts", posts);
        return "posts/index";
    }
    @GetMapping("/posts/show/{id}")
    public String showPost(@PathVariable int id, Model model) {
        Post post = new Post("Test", "This is a test post using a path variable");
        model.addAttribute("post", post);
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
