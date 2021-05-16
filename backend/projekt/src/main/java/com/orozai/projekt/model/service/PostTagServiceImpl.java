package com.orozai.projekt.model.service;

import com.orozai.projekt.exception.DataNotFoundException;
import com.orozai.projekt.model.dto.basic.PostTagDTO;
import com.orozai.projekt.model.dto.basic.TagCountDTO;
import com.orozai.projekt.model.entity.PostTag;
import com.orozai.projekt.model.entity.PostTagForm;
import com.orozai.projekt.model.repository.ICount;
import com.orozai.projekt.model.repository.PostTagRepository;
import com.orozai.projekt.model.repository.TagRepository;
import java.util.Collection;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

@Service
public class PostTagServiceImpl implements IService<PostTagDTO> {
  private final ModelMapper modelMapper;
  private final PostTagRepository postTagRepository;
  private final TagRepository tagRepository;
  private final TagServiceImpl tagService;

  public PostTagServiceImpl(ModelMapper modelMapper,
                            PostTagRepository postTagRepository,
                            TagRepository tagRepository, TagServiceImpl tagService) {
    this.modelMapper = modelMapper;
    this.postTagRepository = postTagRepository;
    this.tagRepository = tagRepository;
    this.tagService = tagService;
  }

  @Override
  public PostTagDTO get(Long id) {
    return modelMapper.map(postTagRepository.findById(id), PostTagDTO.class);
  }

  @Override
  public Collection<PostTagDTO> getAll() {
  return modelMapper.map(postTagRepository.findAll(), new TypeToken<List<PostTagDTO>>(){}.getType());
  }
  public Collection<TagCountDTO> getTop() {
    Collection<ICount> topTags =  postTagRepository.getTopTags();
    return modelMapper.<List<TagCountDTO>>map(topTags, new TypeToken<List<TagCountDTO>>(){}.getType());
  }
  @Override
  public PostTagDTO create(PostTagDTO postTagDTO) {
    return postTagDTO;
  }

  public Collection<PostTagDTO> createMany(PostTagForm postTagForm) {
    for (int x : postTagForm.getTags()) {
      PostTag postTag = new PostTag();
      postTag.setPost(postTagForm.getPost());
      postTag.setTag(tagRepository.findById((long)x).orElseThrow(DataNotFoundException::new));
      postTagRepository.save(postTag);
    }
    return null;
  }
  @Override
  public PostTagDTO update(PostTagDTO postTagDTO) {
    return null;
  }

  @Override
  public void delete(PostTagDTO postTagDTO) {

  }
}
