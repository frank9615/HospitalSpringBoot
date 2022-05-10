package com.example.HospitalSpringBoot.enums.services.impl;

import com.example.HospitalSpringBoot.entities.User;
import com.example.HospitalSpringBoot.repositories.UserRepository;
import com.example.HospitalSpringBoot.enums.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public class UserService implements IUserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> getAll() {
        return (List<User>)this.userRepository.findAll();
    }

    @Override
    public Optional<User> getById(Long id) {
        return this.userRepository.findById(id);
    }

    @Override
    public void save(User user) {
        this.userRepository.save(user);
    }

    @Override
    public void delete(User user) {
        this.userRepository.delete(user);
    }

    @Override
    public User getByUsername(String username) {
        return this.userRepository.findByUsername(username);
    }


}
