package com.chaeyn.spring;

public class HelloResponse {
  private final String message;
  private final String timestamp;

  // 생성자
  public HelloResponse(String message, String timestamp) {
    this.message = message;
    this.timestamp = timestamp;
  }

  // Getter 메서드
  public String getMessage(){
    return message;
  }

  public String getTimestamp(){
    return timestamp;
  }
}
