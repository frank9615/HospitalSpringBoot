package com.example.HospitalSpringBoot.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.io.Serializable;

@Entity
@DiscriminatorValue("ADMIN")
@NoArgsConstructor
@Data
public class Admin extends User implements Serializable {

    public Admin(User user){
        this.setId(user.getId());
        this.setUsername(user.getUsername());
        this.setName(user.getName());
        this.setPassword(user.getPassword());
        this.setSurname(user.getSurname());
        this.setRole(user.getRole());
    }
}
