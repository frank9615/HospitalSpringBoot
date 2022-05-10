package com.example.HospitalSpringBoot.entities;

import lombok.Data;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.Set;

@Entity
@Data
@DiscriminatorValue("OPERATOR")
public class Operator extends User implements Serializable {
    @OneToMany(mappedBy = "operator")
    private Set<Triage> triages;
}
