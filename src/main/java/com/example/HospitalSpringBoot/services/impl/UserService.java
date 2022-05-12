package com.example.HospitalSpringBoot.services.impl;

import com.example.HospitalSpringBoot.entities.User;
import com.example.HospitalSpringBoot.enums.Role;
import com.example.HospitalSpringBoot.repositories.UserRepository;
import com.example.HospitalSpringBoot.services.IUserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.springframework.data.jpa.domain.Specification.where;

@Service

public class UserService implements IUserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> getAll() {
        return  StreamSupport.stream(this.userRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
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

    @Override
    public List<User> findAllByRole(Role role) {
        return this.userRepository.findByRoleEquals(role);
    }

    private static Specification<User> getUsersByUsernameLike(String username) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("USERNAME"), "%" + username + "%");
    }

    private static Specification<User> getUsersByNameLike(String name) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("NAME"), "%" +name + "%" );
    }

    private static Specification<User> getUsersBySurnameLike(String surname) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("SURNAME"), "%" + surname + "%" );
    }

    public Page<User> getAllSpecification(String username, String name, String surname, Pageable page) {
        return userRepository.findAll(where(getUsersByUsernameLike(username)).and(getUsersByNameLike(name)).and(getUsersBySurnameLike(surname)), page);
    }





}
