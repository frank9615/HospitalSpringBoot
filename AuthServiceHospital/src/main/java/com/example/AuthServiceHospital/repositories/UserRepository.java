package com.example.AuthServiceHospital.repositories;

import com.example.AuthServiceHospital.entities.User;
import org.springframework.data.repository.CrudRepository;


import java.util.List;

public interface UserRepository extends CrudRepository<User, Long> {
    public User findByUsername(String username);


}
