package com.example.HospitalSpringBoot.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.Set;

@Entity
@DiscriminatorValue("DOCTOR")
@NoArgsConstructor
@Data
public class Doctor extends User implements Serializable {
    @OneToMany(mappedBy = "doctor")
    private Set<Triage> triages;

    public Doctor(User user){
        this.setId(user.getId());
        this.setUsername(user.getUsername());
        this.setName(user.getName());
        this.setPassword(user.getPassword());
        this.setSurname(user.getSurname());
        this.setRole(user.getRole());
    }

}
