package com.orozai.projekt.model.service;

import com.orozai.projekt.exception.DataNotFoundException;
import com.orozai.projekt.model.dto.basic.UserDTO;
import com.orozai.projekt.model.entity.ERole;
import com.orozai.projekt.model.entity.Role;
import com.orozai.projekt.model.entity.User;
import com.orozai.projekt.model.repository.RoleRepository;
import com.orozai.projekt.model.repository.UserRepository;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements IService<UserDTO>{
  private final ModelMapper modelMapper;
  private final UserRepository userRepository;
  private final RoleRepository roleRepository;
  @Autowired
  private final PasswordEncoder encoder;

  public UserServiceImpl(ModelMapper modelMapper,
      UserRepository userRepository,
      RoleRepository roleRepository, PasswordEncoder encoder) {
    this.modelMapper = modelMapper;
    this.userRepository = userRepository;
    this.roleRepository = roleRepository;
    this.encoder = encoder;
  }

  @Override
  public UserDTO get(Long id) {
    return modelMapper.map(userRepository.findById(id).orElseThrow(DataNotFoundException::new),UserDTO.class);
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

  @Override
  public UserDTO update(UserDTO userDTO) {
    return null;
  }

  @Override
  public void delete(UserDTO userDTO) {

  }
}
