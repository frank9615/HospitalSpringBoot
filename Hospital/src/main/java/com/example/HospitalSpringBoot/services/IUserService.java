package com.example.HospitalSpringBoot.services;

import com.example.HospitalSpringBoot.dtos.UserDto;
import com.example.HospitalSpringBoot.entities.User;
import com.example.HospitalSpringBoot.enums.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;


public interface IUserService {
    public List<UserDto> getAll();
    public User getById(Long id);
    public UserDto getById2(Long id);
    public void save(User user);
    public void delete(User user);
    public User getByUsername(String username);

    public List<UserDto> findAllByRole(Role role);

    public Page<UserDto> getAllSpecification(String username, String name, String surname, Pageable page);
}
