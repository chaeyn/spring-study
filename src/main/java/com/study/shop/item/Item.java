package com.study.shop.item;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@ToString
@Getter
@Setter
@NoArgsConstructor
public class Item {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  // columnDefinition = "TEXT"는 VARCHAR(255)에서 길이 상관없이 타입 지정
  @Column(columnDefinition = "TEXT")
  private String title;

  @Column(nullable = false)
  private Integer price;

  private String username;

  public Item(String title, Integer price, String username) {
    this.title = title;
    this.price = price;
    this.username = username;
  }

}

//- public: 모든 곳에서 사용 가능
//- 아무것도 안 붙임: package-private
//    - 같은 폴더의 클래스에서만 사용 가능
//- private: 다른 클래스에서 사용불가
//- protected: package-private과 같음
//    - 상속한 클래스는 마음대로 사용 가능
//- static: 변수, 클래스를 직접 사용 가능