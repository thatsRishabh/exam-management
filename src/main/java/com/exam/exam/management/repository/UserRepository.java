package com.exam.exam.management.repository;

import com.exam.exam.management.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
   public UserEntity findByUsername(String username);
   Optional<UserEntity> findByEmail(String username);
}
