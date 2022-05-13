package com.example.AuthServiceHospital.services;

import com.example.AuthServiceHospital.entities.User;

public interface IUserService {
    public User getByUsername(String username);

}
