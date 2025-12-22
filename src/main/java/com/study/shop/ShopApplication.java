package com.study.shop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ShopApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShopApplication.class, args);
    Friend test = new Friend("권대형");
    System.out.println(test.name);
	}

}

class Friend {
  String name = "kim";
  int age = 20;
  Friend(String name) {
    this.name = name;
  }
}