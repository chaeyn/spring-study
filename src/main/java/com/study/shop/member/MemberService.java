package com.study.shop.member;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

  private final MemberRepository memberRepository;
  private final PasswordEncoder passwordEncoder;

  public void register(String username, String password, String displayName) {
    String hashedPassword = passwordEncoder.encode(password);
    Member member = new Member(username, hashedPassword, displayName);
    memberRepository.save(member);
  }

}
