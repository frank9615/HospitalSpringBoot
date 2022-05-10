package com.example.HospitalSpringBoot.entities;

import lombok.Data;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.Set;

@Entity
@DiscriminatorValue("DOCTOR")
@Data
public class Doctor extends User implements Serializable {
    @OneToMany(mappedBy = "doctor")
    private Set<Triage> triages;

}
