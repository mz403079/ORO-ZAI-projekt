package com.orozai.projekt.model.service;

import com.orozai.projekt.model.dto.basic.PostLikeDTO;
import com.orozai.projekt.model.dto.basic.TagDTO;
import com.orozai.projekt.model.entity.Post;
import com.orozai.projekt.model.entity.PostLike;
import com.orozai.projekt.model.entity.User;
import com.orozai.projekt.model.repository.PostLikeRepository;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class PostLikeServiceImpl implements IService<PostLikeDTO>{
  private final ModelMapper modelMapper;
  private final PostLikeRepository postLikeRepository;

  public PostLikeServiceImpl(ModelMapper modelMapper,
      PostLikeRepository postLikeRepository) {
    this.modelMapper = modelMapper;
    this.postLikeRepository = postLikeRepository;
  }

  @Override
  public PostLikeDTO get(Long id) {
    return null;
  }

  @Override
  public Collection<PostLikeDTO> getAll() {
    return null;
  }

  public Collection<PostLikeDTO> getByUserId(long userId) {
    return modelMapper.map(postLikeRepository.findAllByUserId(userId), new TypeToken<List<PostLikeDTO>>(){}.getType());
  }
  @Override
  public PostLikeDTO create(PostLikeDTO postLikeDTO) {
    PostLike postLike = modelMapper.map(postLikeDTO, PostLike.class);
    postLikeRepository.save(postLike);
    return postLikeDTO;
  }

  @Override
  public PostLikeDTO update(PostLikeDTO postLikeDTO) {
    return null;
  }

  @Override
  public void delete(PostLikeDTO postLikeDTO) {
    postLikeRepository.delete(modelMapper.map(postLikeDTO,PostLike.class));
  }


  public void handleLike(PostLikeDTO postLikeDTO) {
  Optional<PostLike> postLike =  postLikeRepository.findByUserIdAndPostId(postLikeDTO.getUserId(),postLikeDTO.getPostId());
    if (postLike.isPresent()) {
      delete(postLikeDTO);
      return;
    }
    create(postLikeDTO);
  }
}
