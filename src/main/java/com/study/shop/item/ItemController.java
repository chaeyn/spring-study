package com.study.shop.item;

import com.study.shop.comment.Comment;
import com.study.shop.comment.CommentRepository;
import java.nio.file.AccessDeniedException;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
public class ItemController {

  private final ItemRepository itemRepository;
  private final ItemService itemService;
  private final S3Service s3Service;
  private final CommentRepository commentRepository;

  @GetMapping("list")
  String list(){
    return "redirect:/list/page/1";
  }

  @GetMapping("write")
  String write(){
    return "write.html";
  }

  @PostMapping("add")
  String addPost(String title, Integer price, String imgUrl, Authentication auth) throws AccessDeniedException {
    if (auth == null) {
      throw new AccessDeniedException("로그인을 해주세요");
    }
    itemService.saveItem(title, price, imgUrl, auth.getName());
    return "redirect:/list";
  }

  @GetMapping("detail/{id}")
  String detail(@PathVariable Long id, Model model) {

    List<Comment> comments = commentRepository.findAllByParentId(id);
    Optional<Item> item = itemService.getItemById(id);
    if (item.isPresent()){
      model.addAttribute("item", item.get());
      model.addAttribute("comments", comments);
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

  @GetMapping("list/page/{id}")
  String getListPage(@PathVariable Integer id, Model model){

    Page<Item> result = itemRepository.findPageBy(PageRequest.of(id - 1, 5));
    Integer totalPage = result.getTotalPages();
    model.addAttribute("items", result);
    model.addAttribute("totalPage", totalPage);
    return "list.html";
  }

  @GetMapping("presigned-url")
  @ResponseBody
  String getURL(@RequestParam String filename){
    var result = s3Service.createPresignedUrl("test/" + filename);
    return result;
  }

}
