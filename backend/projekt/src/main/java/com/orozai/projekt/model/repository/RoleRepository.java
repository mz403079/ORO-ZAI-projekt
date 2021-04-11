package com.orozai.projekt.model.repository;

import com.orozai.projekt.model.entity.ERole;
import com.orozai.projekt.model.entity.Role;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
  Optional<Role> findByName(ERole name);
}
