package com.orozai.projekt.model.repository;

import com.orozai.projekt.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}
