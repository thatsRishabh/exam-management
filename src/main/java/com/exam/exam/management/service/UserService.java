package com.exam.exam.management.service;

import com.exam.exam.management.entity.UserEntity;
import com.exam.exam.management.entity.UserRoleEntity;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public interface UserService {

    public UserEntity createUser(UserEntity userEntity, Set<UserRoleEntity> userRoleEntities) throws Exception;

    public UserEntity getByUsername(String username);
}
