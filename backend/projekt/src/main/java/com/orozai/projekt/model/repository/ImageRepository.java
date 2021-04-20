package com.orozai.projekt.model.repository;

import com.orozai.projekt.model.entity.Image;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, Long> {

}
