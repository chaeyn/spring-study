package com.study.shop;

import java.time.LocalDate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class BasicController {
  @GetMapping("/")
  String hello() {
    return "index.html";
  }

  @GetMapping("/about")
  @ResponseBody // 문자 그대로 보여달라는 어노테이션
  String about() {
    return "Spring 공부 사이트입니다";
  }

  @GetMapping("/mypage")
  @ResponseBody
  String mypage() {
    return "마이페이지 입니다";
  }

  @GetMapping("/date")
  @ResponseBody
  LocalDate date() {
    return LocalDate.now();
  }
}
