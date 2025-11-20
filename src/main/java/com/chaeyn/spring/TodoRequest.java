package com.chaeyn.spring;

public class TodoRequest {
  private String text;

  // JSON 역직렬화를 위한 기본 생성자
  public TodoRequest( ) {}

  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }
}
