package com.codeup.codeupspringblog.controllers;

import com.codeup.codeupspringblog.models.Post;
import com.codeup.codeupspringblog.models.User;
import com.codeup.codeupspringblog.repositories.PostRepository;
import com.codeup.codeupspringblog.repositories.UserRepository;
import com.codeup.codeupspringblog.services.EmailService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class PostController {
    private final PostRepository postDao;
    private final UserRepository userDao;
    private final EmailService emailService;


    public PostController(PostRepository postDao, UserRepository userDao, EmailService emailService) {
        this.postDao = postDao;
        this.userDao = userDao;
        this.emailService = emailService;
    }

    @GetMapping("/posts/index")
    public String index(Model model) {
        model.addAttribute("posts", postDao.findAll());
        return "posts/index";
    }
    @GetMapping("/posts/show/{id}")
    public String showPost(@PathVariable int id, Model model) {
        Post post = postDao.findPostById(id);
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
        emailService.prepareAndSend(userPost, userPost.getTitle(), userPost.getBody(), user.getEmail());
        return "redirect:index";
    }
    @GetMapping("/posts/edit/{id}")
    public String editPostForm(@PathVariable("id") long id, Model model) {
        Post post = postDao.findPostById(id);
        model.addAttribute("post", post);
        return "posts/edit";
    }
    @PostMapping("/posts/edit/{id}")
    public String editPost(@ModelAttribute("post") Post post, @PathVariable("id") String id, Model model) {
        Long postId = Long.valueOf(id);
        Post existingPost = postDao.findPostById(postId);

        existingPost.setTitle(post.getTitle());
        existingPost.setBody(post.getBody());

        postDao.save(existingPost);

        return "redirect:/posts/show/{id}";
    }


}
