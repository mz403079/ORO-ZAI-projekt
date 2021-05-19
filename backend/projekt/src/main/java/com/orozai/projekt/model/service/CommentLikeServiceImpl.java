package com.orozai.projekt.model.service;

import com.orozai.projekt.model.dto.basic.CommentLikeDTO;
import com.orozai.projekt.model.entity.Comment;
import com.orozai.projekt.model.entity.CommentLike;
import com.orozai.projekt.model.entity.User;
import com.orozai.projekt.model.repository.CommentLikeRepository;
import java.util.Collection;
import java.util.Optional;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class CommentLikeServiceImpl implements IService<CommentLikeDTO> {

  private final ModelMapper modelMapper;
  private final CommentLikeRepository commentLikeRepository;

  public CommentLikeServiceImpl(ModelMapper modelMapper,
      CommentLikeRepository commentLikeRepository) {
    this.modelMapper = modelMapper;
    this.commentLikeRepository = commentLikeRepository;
  }

  @Override
  public CommentLikeDTO get(Long id) {
    return null;
  }

  @Override
  public Collection<CommentLikeDTO> getAll() {
    return null;
  }

  @Override
  public CommentLikeDTO create(CommentLikeDTO commentLikeDTO) {
    CommentLike commentLike = modelMapper.map(commentLikeDTO, CommentLike.class);
    commentLikeRepository.save(commentLike);
    return commentLikeDTO;
  }

  @Override
  public CommentLikeDTO update(CommentLikeDTO commentLikeDTO) {
    return null;
  }

  @Override
  public void delete(CommentLikeDTO commentLikeDTO) {
    commentLikeRepository.delete(modelMapper.map(commentLikeDTO, CommentLike.class));

  }

  public void handleComment(CommentLikeDTO commentLikeDTO) {
    Optional<CommentLike> commentLike = commentLikeRepository
        .findByUserAndComment(modelMapper.map(commentLikeDTO.getUser(), User.class),
            modelMapper.map(commentLikeDTO.getComment(),
                Comment.class));
    if (commentLike.isPresent()) {
      delete(commentLikeDTO);
      return;
    }
    create(commentLikeDTO);
  }
}
