package com.chaeyn.spring;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

// 이 클래스가 API 요청을 처리하는 핸들러임을 선언
@RestController
@CrossOrigin(origins = "http://localhost:5173")
public class HelloController {
  // 클라이언트에서 요청할 주소
  @GetMapping("/api/hello")
  public HelloResponse sayHello() {
    return new HelloResponse("JSON 데이터 도착", java.time.LocalDateTime.now().toString());
  }
}
