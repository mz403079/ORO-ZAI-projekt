package com.orozai.projekt.controller;

import com.orozai.projekt.model.dto.basic.UserDTO;
import com.orozai.projekt.model.dto.specialized.JwtResponse;
import com.orozai.projekt.model.dto.specialized.LoginFormDTO;
import com.orozai.projekt.model.dto.specialized.RegisterFormDTO;
import com.orozai.projekt.model.repository.RoleRepository;
import com.orozai.projekt.model.repository.UserRepository;
import com.orozai.projekt.model.service.UserDetailsImpl;
import com.orozai.projekt.model.service.UserServiceImpl;
import com.orozai.projekt.security.JwtUtils;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
  @Autowired
  private final AuthenticationManager authenticationManager;

  @Autowired
  private final JwtUtils jwtUtils;

  private final ModelMapper modelMapper;

  private final UserServiceImpl userService;

  public AuthController(
      AuthenticationManager authenticationManager,
      JwtUtils jwtUtils, ModelMapper modelMapper,
      UserServiceImpl userService) {
    this.authenticationManager = authenticationManager;
    this.jwtUtils = jwtUtils;
    this.modelMapper = modelMapper;
    this.userService = userService;
  }

  @PostMapping("/signin")
  public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginFormDTO loginFormDTO) {
    Authentication authentication = authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(loginFormDTO.getUsername(), loginFormDTO.getPassword()));
    SecurityContextHolder.getContext().setAuthentication(authentication);

    String jwt = jwtUtils.generateJwtToken(authentication);

    UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

    List<String> roles = userDetails.getAuthorities().stream()
        .map(item -> item.getAuthority())
        .collect(Collectors.toList());

    return ResponseEntity.ok(new JwtResponse(jwt,
        userDetails.getUserId(),
        userDetails.getUsername(),
        userDetails.getEmail(),
        userDetails.getPicId(),
        roles));
  }

  @PostMapping("/signup")
  public ResponseEntity<?> registerUser(@Valid @RequestBody RegisterFormDTO registerFormDTO) {
//    if (userRepository.existsByUsername(registerFormDTO.getUsername())) {
//      return ResponseEntity
//          .badRequest()
//          .body(new MessageResponse("Error: Username is already taken!"));
//    }
//
//    if (userRepository.existsByEmail(registerFormDTO.getEmail())) {
//      return ResponseEntity
//          .badRequest()
//          .body(new MessageResponse("Error: Email is already in use!"));
//    }
//
//    // Create new user's account
    UserDTO userDTO = modelMapper.map(registerFormDTO, UserDTO.class);
    userService.create(userDTO);

//    return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    return new ResponseEntity<>(HttpStatus.OK);
  }

}
