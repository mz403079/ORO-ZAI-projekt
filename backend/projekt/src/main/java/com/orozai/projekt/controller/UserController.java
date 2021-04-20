package com.orozai.projekt.controller;

import com.orozai.projekt.model.dto.basic.UserDTO;
import com.orozai.projekt.model.service.UserServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/profile")
public class UserController {
  private final UserServiceImpl userService;

  public UserController(UserServiceImpl userService) {
    this.userService = userService;
  }

  @GetMapping("/{id}")
  public ResponseEntity<UserDTO> getProfilePage(@PathVariable("id") Long userID) {
    UserDTO userDTO = userService.get(userID);
    return new ResponseEntity<>(userDTO,HttpStatus.OK);
  }

  @PostMapping("/{id}/pic")
  public ResponseEntity<String> updateUserData(@PathVariable("id") Long userID,
      @RequestParam(name="file") MultipartFile file) {
      userService.updateProfilePic(userID,file);

    return new ResponseEntity<>("pic changed", HttpStatus.OK);
  }
}
