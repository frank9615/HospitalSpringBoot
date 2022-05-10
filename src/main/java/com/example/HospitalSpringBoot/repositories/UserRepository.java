package com.example.HospitalSpringBoot.repositories;

import com.example.HospitalSpringBoot.entities.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
    public User findByUsername(String username);
}
