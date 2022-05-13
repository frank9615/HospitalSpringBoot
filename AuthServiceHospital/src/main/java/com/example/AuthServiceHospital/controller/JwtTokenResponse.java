package com.example.AuthServiceHospital.controller;

import com.example.AuthServiceHospital.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class JwtTokenResponse implements Serializable {
    private static final long serialVersionUID = 8317676219297719109L;
    private final String token;
    private Long id;
    private String username;
    private String firstname;
    private String lastName;
    private Role role;

    public JwtTokenResponse(String token){
        this.token = token;
    }


}
