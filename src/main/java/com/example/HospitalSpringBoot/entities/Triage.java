package com.example.HospitalSpringBoot.entities;


import com.example.HospitalSpringBoot.enums.TriageColor;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Data
public class Triage implements Serializable {
    @Id
    @GeneratedValue(generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private Long id;
    @Enumerated(EnumType.STRING)
    private TriageColor triageColor;
    @Basic
    private String notes;
    @Temporal(TemporalType.DATE)
    private Date triageDate;
    @ManyToOne
    @JoinColumn(name = "patient_id", referencedColumnName = "id")
    private Patient patient;
    @ManyToOne
    @JoinColumn(name = "doctor_id", referencedColumnName = "id")
    private Doctor doctor;
    @ManyToOne
    @JoinColumn(name ="operator_id", referencedColumnName = "id")
    private Operator operator;
}
