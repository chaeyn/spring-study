package com.study.shop.post;

import com.study.shop.item.ItemRepository;
import com.study.shop.item.ItemService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class PostController {

  private final PostRepository postRepository;
  private final PostService postService;

  @GetMapping("posts")
  String posts(Model model) {
    List<Post> result = postService.getAllPosts();
    model.addAttribute("posts", result);
    return "posts.html";
  }
}
