package com.exam.exam.management.controller;


import com.exam.exam.management.entity.RoleEntity;
import com.exam.exam.management.entity.UserEntity;
import com.exam.exam.management.entity.UserRoleEntity;
import com.exam.exam.management.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/adduser")
    public UserEntity createUser(@RequestBody UserEntity userEntity) throws Exception {
        Set<UserRoleEntity> roles = new HashSet<>();

        RoleEntity role = new RoleEntity();
        role.setRoleId(45L);
        role.setRoleName("Normal");

        UserRoleEntity userRoleEntity = new UserRoleEntity();
        userRoleEntity.setUserEntity(userEntity);
        userRoleEntity.setRoleEntity(role);

        roles.add(userRoleEntity);

        return this.userService.createUser(userEntity, roles);
    }

    @GetMapping("/getbyusername/{username}")
    public UserEntity getUsername( @PathVariable String username)
    {

        return userService.getByUsername(username);

    }

}