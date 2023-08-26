package com.exam.exam.management.controller;


import com.exam.exam.management.entity.RoleEntity;
import com.exam.exam.management.entity.UserEntity;
import com.exam.exam.management.entity.UserRoleEntity;
import com.exam.exam.management.request.AuthRequest;
//import com.exam.exam.management.service.JwtService;
import com.exam.exam.management.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;
//    @Autowired
//    private JwtService jwtService;
//
//    @Autowired
//    private AuthenticationManager authenticationManager;

//    @PostMapping("/adduser")
//    public UserEntity createUser(@RequestBody UserEntity userEntity) throws Exception {
//        Set<UserRoleEntity> roles = new HashSet<>();
//
//        RoleEntity role = new RoleEntity();
//        role.setRoleId(45L);
//        role.setRoleName("Normal");
//
//        UserRoleEntity userRoleEntity = new UserRoleEntity();
//        userRoleEntity.setUserEntity(userEntity);
//        userRoleEntity.setRoleEntity(role);
//
//        roles.add(userRoleEntity);
//
//        return this.userService.createUser(userEntity, roles);
//    }

    @PostMapping("/adduser")
    public UserEntity createUser(@RequestBody UserEntity userEntity) {

        return this.userService.createUser(userEntity);
    }

//    @PostMapping("/login")
//    public String authenticateAndGetToken(@RequestBody AuthRequest authRequest) {
//
//        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
//        if (authentication.isAuthenticated()) {
//            return jwtService.generateToken(authRequest.getUsername());
//        } else {
//            throw new UsernameNotFoundException("invalid user request !");
//        }
//
//
//    }
    @GetMapping("/getbyusername/{username}")
    public UserEntity getUsername( @PathVariable String username)
    {

        return userService.getByUsername(username);

    }


}