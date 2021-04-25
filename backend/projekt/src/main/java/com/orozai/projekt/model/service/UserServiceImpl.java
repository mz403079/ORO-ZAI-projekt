package com.orozai.projekt.model.service;

import com.orozai.projekt.exception.DataNotFoundException;
import com.orozai.projekt.model.dto.basic.PostDTO;
import com.orozai.projekt.model.dto.basic.UserDTO;
import com.orozai.projekt.model.entity.ERole;
import com.orozai.projekt.model.entity.Image;
import com.orozai.projekt.model.entity.Post;
import com.orozai.projekt.model.entity.Role;
import com.orozai.projekt.model.entity.User;
import com.orozai.projekt.model.repository.ImageRepository;
import com.orozai.projekt.model.repository.RoleRepository;
import com.orozai.projekt.model.repository.UserRepository;
import java.io.IOException;
import java.util.Base64;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
public class UserServiceImpl implements IService<UserDTO>{
  private final ModelMapper modelMapper;
  private final UserRepository userRepository;
  private final RoleRepository roleRepository;
  private final ImageRepository imageRepository;
  private final ImageServiceImpl imageService;

  @Autowired
  private final PasswordEncoder encoder;

  public UserServiceImpl(ModelMapper modelMapper,
      UserRepository userRepository,
      RoleRepository roleRepository,
      ImageRepository imageRepository,
      ImageServiceImpl imageService, PasswordEncoder encoder) {
    this.modelMapper = modelMapper;
    this.userRepository = userRepository;
    this.roleRepository = roleRepository;
    this.imageRepository = imageRepository;
    this.imageService = imageService;
    this.encoder = encoder;
  }

  @Override
  public UserDTO get(Long id) {
    User user = userRepository.findById(id).orElseThrow(DataNotFoundException::new);
    return userToUserDTO(user);
  }

  @Override
  public Collection<UserDTO> getAll() {
    return null;
  }

  @Override
  public UserDTO create(UserDTO userDTO) {
    User user = modelMapper.map(userDTO, User.class);
    user.setPassword(encoder.encode(user.getPassword()));
    Set<Role> roles = new HashSet<>();
    Role userRole = roleRepository.findByName(ERole.ROLE_USER)
        .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
    roles.add(userRole);
    user.setRoles(roles);
    userRepository.save(user);
    return null;
  }

  public void updateProfilePic(Long userId, MultipartFile file) {
    User user = userRepository.findById(userId).orElseThrow(DataNotFoundException::new);
    Image image = imageService.create(file);
    user.setProfileImage(image);
    userRepository.save(user);

  }
  @Override
  public UserDTO update(UserDTO userDTO) {
    return null;
  }

  public UserDTO userToUserDTO(User user) {
    UserDTO userDTO = modelMapper.map(user,UserDTO.class);
    userDTO.setProfileImage(imageService.imageToImageDTO(user.getProfileImage()));
    return userDTO;
  }
  @Override
  public void delete(UserDTO userDTO) {

  }
}
