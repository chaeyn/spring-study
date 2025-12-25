package com.study.shop.item;

import com.study.shop.member.MemberService;
import java.nio.file.AccessDeniedException;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
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
  private final MemberService memberService;

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
  String addPost(String title, Integer price, Authentication auth) throws AccessDeniedException {
    if (auth == null) {
      throw new AccessDeniedException("로그인을 해주세요");
    }
    itemService.saveItem(title, price, auth.getName());
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
