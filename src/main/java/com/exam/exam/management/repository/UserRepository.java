package com.exam.exam.management.repository;

import com.exam.exam.management.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
   public UserEntity findByUsername(String username);
}
