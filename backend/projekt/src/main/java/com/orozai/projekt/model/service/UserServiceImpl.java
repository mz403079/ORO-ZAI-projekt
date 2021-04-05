package com.orozai.projekt.model.service;

import com.orozai.projekt.exception.DataNotFoundException;
import com.orozai.projekt.model.dto.basic.UserDTO;
import com.orozai.projekt.model.repository.UserRepository;
import java.util.Collection;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements IService<UserDTO>{
  private final ModelMapper modelMapper;
  private final UserRepository userRepository;

  public UserServiceImpl(ModelMapper modelMapper,
      UserRepository userRepository) {
    this.modelMapper = modelMapper;
    this.userRepository = userRepository;
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
