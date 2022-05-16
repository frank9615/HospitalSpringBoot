package com.example.HospitalSpringBoot.services;

import com.example.HospitalSpringBoot.dtos.UserDto;
import com.example.HospitalSpringBoot.entities.User;
import com.example.HospitalSpringBoot.enums.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Optional;


public interface IUserService {
    public List<User> getAll();
    public User getById(Long id);
    public void save(User user);
    public void delete(User user);
    public User getByUsername(String username);

    public List<User> findAllByRole(Role role);

    public Specification<User> getUsersByUsernameLike(String username);
    public  Specification<User> getUsersByNameLike(String name);
    public  Specification<User> getUsersBySurnameLike(String surname);

    public Page<User> getAllSpecification(String username, String name, String surname, Pageable page);
}
