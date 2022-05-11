package com.example.HospitalSpringBoot.repositories;

import com.example.HospitalSpringBoot.entities.User;
import com.example.HospitalSpringBoot.enums.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.lang.NonNull;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Long> {
    public User findByUsername(String username);

    List<User> findByRoleEquals(@NonNull Role role);

}
