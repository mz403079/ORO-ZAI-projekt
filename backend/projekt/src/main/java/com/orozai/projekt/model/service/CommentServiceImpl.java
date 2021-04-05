package com.orozai.projekt.model.service;

import com.orozai.projekt.model.dto.basic.CommentDTO;
import java.util.Collection;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements IService<CommentDTO> {
  private final ModelMapper modelMapper;

  public CommentServiceImpl(ModelMapper modelMapper) {
    this.modelMapper = modelMapper;
  }

  @Override
  public CommentDTO get(Long id) {
    return null;
  }

  @Override
  public Collection<CommentDTO> getAll() {
    return null;
  }

  @Override
  public CommentDTO create(CommentDTO commentDTO) {
    return null;
  }

  @Override
  public CommentDTO update(CommentDTO commentDTO) {
    return null;
  }

  @Override
  public void delete(CommentDTO commentDTO) {

  }
}
