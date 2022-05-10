package com.example.HospitalSpringBoot.entities;

import lombok.Data;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.io.Serializable;

@Entity
@DiscriminatorValue("ADMIN")
@Data
public class Admin extends User implements Serializable {
}
