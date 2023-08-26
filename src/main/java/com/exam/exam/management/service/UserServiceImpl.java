package com.exam.exam.management.service;

import com.exam.exam.management.entity.UserEntity;
import com.exam.exam.management.entity.UserRoleEntity;
import com.exam.exam.management.repository.RoleRepository;
import com.exam.exam.management.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

//    @Autowired
//    private PasswordEncoder passwordEncoder;

//    @Override
//    public UserEntity createUser(UserEntity userEntity, Set<UserRoleEntity> userRoleEntities) throws Exception {
//
//        UserEntity local= this.userRepository.findByUsername(userEntity.getUsername());
//
//        if (local !=null){
//            System.out.println("User is already there");
//            throw new Exception("User already present");
//        }else {
//            for (UserRoleEntity ur : userRoleEntities){
//                roleRepository.save(ur.getRoleEntity());
//            }
//            userEntity.getUserRoleEntities().addAll(userRoleEntities);
//            local =this.userRepository.save(userEntity);
//        }
//        return local;
//    }

    @Override
    public UserEntity createUser(UserEntity userEntity) {

//        userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
        return this.userRepository.save(userEntity);
//        userInfo.setPassword(passwordEncoder.encode(userInfo.getPassword()));
//        repository.save(userInfo);
//        return "user added to system ";
    }


    @Override
    public UserEntity getByUsername(String username) {
        return this.userRepository.findByUsername(username);
    }
}
