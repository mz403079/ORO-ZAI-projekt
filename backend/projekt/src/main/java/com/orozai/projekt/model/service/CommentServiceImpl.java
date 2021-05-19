package com.orozai.projekt.model.service;

import com.orozai.projekt.model.dto.basic.CommentDTO;
import com.orozai.projekt.model.entity.Comment;
import com.orozai.projekt.model.repository.CommentRepository;
import java.util.Collection;
import java.util.Set;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements IService<CommentDTO> {

  private final ModelMapper modelMapper;
  private final CommentRepository commentRepository;

  public CommentServiceImpl(ModelMapper modelMapper,
      CommentRepository commentRepository) {
    this.modelMapper = modelMapper;
    this.commentRepository = commentRepository;
  }

  @Override
  public CommentDTO get(Long id) {
    return null;
  }

  @Override
  public Collection<CommentDTO> getAll() {
    return modelMapper.map(commentRepository.findAll(), new TypeToken<Set<CommentDTO>>() {
    }.getType());
  }

  @Override
  public CommentDTO create(CommentDTO commentDTO) {

    Comment comment = modelMapper.map(commentDTO, Comment.class);
    commentRepository.save(comment);
    return commentDTO;
  }

  @Override
  public CommentDTO update(CommentDTO commentDTO) {
    return null;
  }

  @Override
  public void delete(CommentDTO commentDTO) {

  }
}
