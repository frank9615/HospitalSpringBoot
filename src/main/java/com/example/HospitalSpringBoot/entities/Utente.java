package com.example.HospitalSpringBoot.entities;

import com.example.HospitalSpringBoot.enums.Role;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class Utente {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    private String username;
    private String password;
    private Role role;

}
