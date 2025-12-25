package com.study.shop.member;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

  private final MemberRepository memberRepository;

  public void register(String username, String password, String displayName) {
    String hashedPassword = new BCryptPasswordEncoder().encode(password);
    Member member = new Member(username, hashedPassword, displayName);
    memberRepository.save(member);
  }

}
