package com.orozai.projekt.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class Test {
  @GetMapping(value = "/test")
  public String tester() {

    System.out.println("Subjekt /test");
    System.out.println("Subjekt /test");
    return "forward:/google.com";
  }
}