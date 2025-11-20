package com.chaeyn.spring;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity // 이 클래스가 데이터베이스의 테이블
public class Todo {
  @Id // PK
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false) // 필드(컬럼) null 허용 x
  private String text;

  private boolean completed = false;

  // JPA 사용을 위해 기본 생성자 필수
  protected Todo(){}

  // 데이터 생성을 위한 생성자
  public Todo(String text){
    this.text = text;
  }

  // Getter 메서드
  public Long getId() {
    return id;
  }

  public String getText() {
    return text;
  }

  public boolean isCompleted() {
    return completed;
  }

  public void setCompleted(boolean completed) {
    this.completed = completed;
  }

  public void updateText(String text){
    this.text = text;
  }
}
