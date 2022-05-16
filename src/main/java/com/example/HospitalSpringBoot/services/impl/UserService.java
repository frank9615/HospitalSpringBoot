package com.example.HospitalSpringBoot.services.impl;

import com.example.HospitalSpringBoot.dtos.UserDto;
import com.example.HospitalSpringBoot.entities.User;
import com.example.HospitalSpringBoot.enums.Role;
import com.example.HospitalSpringBoot.repositories.UserRepository;
import com.example.HospitalSpringBoot.services.IUserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.springframework.data.jpa.domain.Specification.where;

@Service

public class UserService implements IUserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> getAll() {
        return StreamSupport.stream(this.userRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    @Override
    public User getById(Long id) {
        return this.userRepository.findById(id).get();
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

    @Override
    public List<User> findAllByRole(Role role) {
        return this.userRepository.findByRoleEquals(role);


    }
    @Override
    public  Specification<User> getUsersByUsernameLike(String username) {
        if(username == null ){
            return null;
        }
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("username"), "%" + username + "%");
    }

    @Override
    public  Specification<User> getUsersByNameLike(String name) {
        if(name == null ){
            return null;
        }
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("name"), "%" +name + "%" );
    }

    @Override
    public  Specification<User> getUsersBySurnameLike(String surname) {
        if(surname == null ){
            return null;
        }
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("surname"), "%" + surname + "%" );
    }

    public Page<User> getAllSpecification(String username, String name, String surname, Pageable page) {
        Page<User> users = userRepository.findAll(where(getUsersByUsernameLike(username)).and(getUsersByNameLike(name)).and(getUsersBySurnameLike(surname)), page);
        return users;
    }





}
