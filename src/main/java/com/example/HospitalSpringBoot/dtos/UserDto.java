package com.example.HospitalSpringBoot.dtos;

import com.example.HospitalSpringBoot.enums.Role;
import lombok.Data;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Data
public class UserDto {
    private Long id;
    private String username;
    private String password;
    private String name;
    private String surname;
    private Role role;
}
