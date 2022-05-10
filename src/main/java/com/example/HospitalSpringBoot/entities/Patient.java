package com.example.HospitalSpringBoot.entities;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sun.istack.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
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
    @NotNull
    private Date registrationdate;
    @OneToMany(mappedBy = "patient")
    @JsonIgnoreProperties
    private Set<Triage> triages;
}
