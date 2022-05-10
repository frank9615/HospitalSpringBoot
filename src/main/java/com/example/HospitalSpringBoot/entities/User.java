package com.example.HospitalSpringBoot.entities;

import com.example.HospitalSpringBoot.enums.Role;
import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@ToString
public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private Long id;

    @Column(unique = true)
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
