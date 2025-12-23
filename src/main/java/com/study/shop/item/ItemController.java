package com.study.shop.item;

import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class ItemController {

  private final ItemRepository itemRepository;
  private final ItemService itemService;

  @GetMapping("list")
  String list(Model model){
    List<Item> items = itemService.getAllItem();
    model.addAttribute("items", items);
    return "list.html";
  }

  @GetMapping("write")
  String write(Model model){
    return "write.html";
  }

  @PostMapping("add")
  String addPost(String title, Integer price) {
    itemService.saveItem(title, price);
    return "redirect:/list";
  }

  @GetMapping("detail/{id}")
  String detail(@PathVariable Long id, Model model) {

    Optional<Item> item = Optional.ofNullable(itemService.getItemById(id));
    if (item.isPresent()){
      model.addAttribute("item", item.get());
      return "detail.html";
    } else {
      return "redirect:/list";
    }
  }
}
