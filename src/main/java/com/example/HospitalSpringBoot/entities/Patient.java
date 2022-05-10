package com.example.HospitalSpringBoot.entities;


import com.sun.istack.NotNull;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Entity
@Data
public class Patient implements Serializable {
    @Id
    @GeneratedValue(generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private Long id;
    @Basic
    @Column(unique = true)
    private String cf;
    @Basic
    private String name;
    @Basic
    private String surname;
    @Temporal(TemporalType.DATE)
    @NotNull
    private Date birthday;
    @Temporal(TemporalType.DATE)
    private Date registrationDate;
    @OneToMany(mappedBy = "patient")
    private Set<Triage> triages;
}