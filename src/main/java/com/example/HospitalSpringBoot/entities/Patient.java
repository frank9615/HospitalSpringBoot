package com.example.HospitalSpringBoot.entities;



import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import javax.validation.constraints.NotNull;

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
    @NotNull
    private String cf;
    @Basic
    @NotNull
    private String name;
    @Basic
    @NotNull
    private String surname;
    @Temporal(TemporalType.DATE)
    @NotNull
    private Date birthday;
    @Temporal(TemporalType.DATE)
    private Date registrationdate;
    @OneToMany(mappedBy = "patient")
    @JsonManagedReference
    private Set<Triage> triages;
}
