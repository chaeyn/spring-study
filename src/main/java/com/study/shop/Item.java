package com.study.shop;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Item {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  public Long id;

  // columnDefinition = "TEXT"는 VARCHAR(255)에서 길이 상관없이 타입 지정
  @Column(columnDefinition = "TEXT")
  public String title;

  @Column(nullable = false)
  public Integer price;
}
