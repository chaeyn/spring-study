package com.study.shop.member;

import java.util.HashMap;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
public class MemberController {

  private final MemberService memberService;
  private final MemberRepository memberRepository;

  @GetMapping("register")
  String register(Authentication auth) {
    if (auth.isAuthenticated()) {
      return "redirect:/list";
    }
    return "register.html";
  }

  @PostMapping("member")
  String addMember(String username, String password, String displayName) throws Exception {
    memberService.register(username, password, displayName);
    return "redirect:/list";
  }

  @GetMapping("login")
  public String login() {
    return "login.html";
  }

  @GetMapping("/my-page")
  public String myPage(Authentication auth) {
    CustomUser result = (CustomUser) auth.getPrincipal();
    if (auth.isAuthenticated()) {
      return "my-page.html";
    }
    return "redirect:/list";
  }

  @GetMapping("/user/{id}")
  @ResponseBody
  public MemberDto getUser(@PathVariable Long id) {
    Optional<Member> result = memberRepository.findById(id);

      Member member = result.get();
      MemberDto data = new MemberDto(member.getUsername(), member.getDisplayName());

      return data;
  }
}

class MemberDto {
  public String username;
  public String displayName;

  MemberDto(String username, String displayName) {
    this.username = username;
    this.displayName = displayName;
  }
}