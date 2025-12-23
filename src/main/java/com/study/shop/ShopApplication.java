package com.study.shop;

import com.study.shop.test.People;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ShopApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShopApplication.class, args);

    People people = new People();
    people.setName("홍준기");
    people.setAge(99);
    people.addAge();
    people.addAge();
    System.out.println(people.getName() + people.getAge());
	}

}