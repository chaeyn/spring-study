package com.study.shop.item;

import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

    Optional<Item> item = itemService.getItemById(id);
    if (item.isPresent()){
      model.addAttribute("item", item.get());
      return "detail.html";
    } else {
      return "redirect:/list";
    }
  }

  @GetMapping("edit/{id}")
  String edit(@PathVariable Long id, Model model) {

    Optional<Item> item = itemRepository.findById(id);
    if (item.isPresent()){
      model.addAttribute("item", item.get());
      return "edit.html";
    } else {
      return "redirect:/list";
    }
  }

  @PostMapping("edit")
  String editItem(Long id, String title, Integer price) {
    itemService.editItem(id, title, price);
    return "redirect:/list";
  }

  @DeleteMapping("item")
  ResponseEntity<String> deleteItem(@RequestParam Long id){
      itemRepository.deleteById(id);
      return ResponseEntity.status(200).body("삭제완료");
  }
}
