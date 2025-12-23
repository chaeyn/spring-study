package com.study.shop.item;

import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class ItemController {

  private final ItemRepository itemRepository;

  @GetMapping("list")
  String list(Model model){
    List<Item> result = itemRepository.findAll();
    model.addAttribute("items", result);
    return "list.html";
  }

  @GetMapping("write")
  String write(Model model){
    return "write.html";
  }

  @PostMapping("add")
  String addPost(
      @RequestParam String title,
      @RequestParam Integer price) {
    Item item = new Item(title, price);
    itemRepository.save(item);
    return "redirect:/list";
  }

  @GetMapping("detail/{id}")
  String detail(@PathVariable Long id, Model model) {

    Optional<Item> item = itemRepository.findById(id);
    if (item.isPresent()){
      model.addAttribute("item", item.get());
      return "detail.html";
    } else {
      return "redirect:/list";
    }
  }
}
