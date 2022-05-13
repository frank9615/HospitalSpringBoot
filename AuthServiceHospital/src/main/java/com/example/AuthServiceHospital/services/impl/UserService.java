package com.example.AuthServiceHospital.services.impl;


import com.example.AuthServiceHospital.entities.User;
import com.example.AuthServiceHospital.repositories.UserRepository;
import com.example.AuthServiceHospital.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserService implements IUserService {
    @Autowired
    private UserRepository userRepository;


    @Override
    public User getByUsername(String username) {
        return this.userRepository.findByUsername(username);
    }







}
