package com.codeup.codeupspringblog.controllers;

import com.codeup.codeupspringblog.models.Post;
import com.codeup.codeupspringblog.models.User;
import com.codeup.codeupspringblog.repositories.PostRepository;
import com.codeup.codeupspringblog.repositories.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
public class PostController {
    private final PostRepository postDao;
    private final UserRepository userDao;

    public PostController(PostRepository postDao, UserRepository userDao) {
        this.postDao = postDao;
        this.userDao = userDao;
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
User user = userDao.findUserById(2L);
        Post userPost = new Post(post.getTitle(), post.getBody(), user);
        postDao.save(userPost);
        return "redirect:index";
    }

}
