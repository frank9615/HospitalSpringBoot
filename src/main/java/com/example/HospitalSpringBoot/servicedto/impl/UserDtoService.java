package com.example.HospitalSpringBoot.servicedto.impl;

import com.example.HospitalSpringBoot.dtos.UserDto;
import com.example.HospitalSpringBoot.entities.User;
import com.example.HospitalSpringBoot.enums.Role;
import com.example.HospitalSpringBoot.repositories.UserRepository;
import com.example.HospitalSpringBoot.servicedto.IUserDtoService;
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
public class UserDtoService implements IUserDtoService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private IUserService userService;

    @Override
    public List<UserDto> getAll() {
        List<UserDto> users = this.userService.getAll().stream()
                .map(source -> modelMapper.map(source, UserDto.class))
                .collect(Collectors.toList());
        return users;
    }


    @Override
    public UserDto getById(Long id) {
        return modelMapper.map(this.userService.getById(id), UserDto.class);
    }


    @Override
    public List<UserDto> findAllByRole(Role role) {
        List<UserDto> users = this.userService.findAllByRole(role).stream()
                .map(source -> modelMapper.map(source, UserDto.class))
                .collect(Collectors.toList());
        return users;

    }

    public Page<UserDto> getAllSpecification(String username, String name, String surname, Pageable page) {
        Page<User> users = this.userService.getAllSpecification(username, name, surname , page);
        Page<UserDto> usersdto = new PageImpl<UserDto>(users
                .getContent()
                .stream()
                .map(source -> modelMapper.map(source, UserDto.class)).collect(Collectors.toList()), users.getPageable(), users.getSize());

        return usersdto;
    }

}
