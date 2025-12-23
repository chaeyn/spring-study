package com.study.shop.test;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class People {

  private String name;

  private Integer age;

  public void addAge() {
    if (age < 100) {
      this.age = this.age + 1;
    }
  }

  public void setAge(Integer age) {
    if (age >= 0 && age < 100) {
      this.age = age;
    }
  }

}