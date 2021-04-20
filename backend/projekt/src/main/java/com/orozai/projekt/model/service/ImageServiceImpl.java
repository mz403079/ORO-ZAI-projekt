package com.orozai.projekt.model.service;

import com.orozai.projekt.exception.DataNotFoundException;
import com.orozai.projekt.model.dto.basic.ImageDTO;
import com.orozai.projekt.model.entity.Image;
import com.orozai.projekt.model.repository.ImageRepository;
import java.io.IOException;
import java.util.Base64;
import java.util.Collection;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ImageServiceImpl implements IService<ImageDTO> {

  private final ModelMapper modelMapper;
  private final ImageRepository imageRepository;

  public ImageServiceImpl(ModelMapper modelMapper, ImageRepository imageRepository) {
    this.modelMapper = modelMapper;
    this.imageRepository = imageRepository;
  }

  @Override
  public ImageDTO get(Long id) {
    Image image = imageRepository.findById(id).orElseThrow(DataNotFoundException::new);
    ImageDTO imageDTO = modelMapper.map(image,ImageDTO.class);
    byte[] imageData = image.getImageData();
    if(imageData != null && imageData.length > 0)
      imageDTO.setImageData(new String(imageData));
    else
      imageDTO.setImageData(null);
    return imageDTO;
  }

  @Override
  public Collection<ImageDTO> getAll() {
    return null;
  }

  @Override
  public ImageDTO create(ImageDTO imageDTO) {
    return null;
  }

  public Image create(MultipartFile file) {
    if (file != null) {
      String fileName = StringUtils.cleanPath(file.getOriginalFilename());
      if (fileName.contains("..")) {
        System.out.println("not a a valid file");
      }
      try {
        Image image = new Image();
        image.setImageData(Base64.getEncoder().encode(file.getBytes()));
        imageRepository.save(image);
        return image;
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
    return null;
  }
  @Override
  public ImageDTO update(ImageDTO imageDTO) {
    return null;
  }

  @Override
  public void delete(ImageDTO imageDTO) {

  }
}
