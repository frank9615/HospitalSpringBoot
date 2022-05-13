package com.example.AuthServiceHospital.entities;

import com.example.AuthServiceHospital.enums.Role;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private Long id;

    @Column(unique = true)

    @Basic
    private String username;

    @Basic
    private String password;

    @Basic
    private String name;

    @Basic
    private String surname;

    @Enumerated(EnumType.STRING)
    private Role role;

}
