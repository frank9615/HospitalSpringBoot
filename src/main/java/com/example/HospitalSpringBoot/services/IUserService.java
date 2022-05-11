package com.example.HospitalSpringBoot.services;

import com.example.HospitalSpringBoot.entities.User;
import com.example.HospitalSpringBoot.enums.Role;

import java.util.List;
import java.util.Optional;


public interface IUserService {
    public List<User> getAll();
    public Optional<User> getById(Long id);
    public void save(User user);
    public void delete(User user);
    public User getByUsername(String username);

    public List<User> findAllByRole(Role role);
}
