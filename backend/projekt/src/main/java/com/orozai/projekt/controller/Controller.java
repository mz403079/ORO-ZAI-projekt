//package com.orozai.projekt.controller;
//
//import com.orozai.projekt.model.dto.basic.SubjectDTO;
//import com.orozai.projekt.model.entity.Subject;
//import java.util.ArrayList;
//import java.util.Collection;
//import java.util.HashSet;
//import java.util.List;
//import java.util.Set;
//import org.modelmapper.TypeToken;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import com.orozai.projekt.model.service.SubjectServiceImpl;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//
//@RestController
//@RequestMapping("/api")
//public class Controller {
//  private final SubjectServiceImpl subjectService;
//  @Autowired
//  public Controller(SubjectServiceImpl subjectService) {
//    this.subjectService = subjectService;
//  }
//  @GetMapping(value = "/subjects")
//  public Collection<SubjectDTO> getOffer() {
//
//    System.out.println("Subjekt /api");
//    System.out.println(subjectService.getAll());
//    System.out.println("Subjekt /api2");
//  return subjectService.getAll();
//  }
//}
