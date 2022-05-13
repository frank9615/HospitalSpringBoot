package com.example.HospitalSpringBoot.services.impl;

import com.example.HospitalSpringBoot.dtos.PatientDto;
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
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.springframework.data.jpa.domain.Specification.where;

@Service

public class UserService implements IUserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<UserDto> getAll() {
        List<UserDto> users = StreamSupport.stream(this.userRepository.findAll().spliterator(), false)
                .map(source -> modelMapper.map(source, UserDto.class))
                .collect(Collectors.toList());
        return users;
    }

    @Override
    public User getById(Long id) {
        return this.userRepository.findById(id).get();
    }

    @Override
    public UserDto getById2(Long id) {
        return modelMapper.map(this.userRepository.findById(id).get(), UserDto.class);
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
    public List<UserDto> findAllByRole(Role role) {
        List<UserDto> users = StreamSupport.stream(this.userRepository.findByRoleEquals(role).spliterator(), false)
                .map(source -> modelMapper.map(source, UserDto.class))
                .collect(Collectors.toList());
        return users;

    }

    private static Specification<User> getUsersByUsernameLike(String username) {
        if(username == null ){
            return null;
        }
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("username"), "%" + username + "%");
    }

    private static Specification<User> getUsersByNameLike(String name) {
        if(name == null ){
            return null;
        }
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("name"), "%" +name + "%" );
    }

    private static Specification<User> getUsersBySurnameLike(String surname) {
        if(surname == null ){
            return null;
        }
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("surname"), "%" + surname + "%" );
    }

    public Page<UserDto> getAllSpecification(String username, String name, String surname, Pageable page) {
        Page<User> users = userRepository.findAll(where(getUsersByUsernameLike(username)).and(getUsersByNameLike(name)).and(getUsersBySurnameLike(surname)), page);
        Page<UserDto> usersdto = new PageImpl<UserDto>(users
                .getContent()
                .stream()
                .map(source -> modelMapper.map(source, UserDto.class)).collect(Collectors.toList()), users.getPageable(), users.getSize());

        return usersdto;
    }





}
