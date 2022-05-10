package com.example.HospitalSpringBoot.dtos;

import com.example.HospitalSpringBoot.entities.Triage;
import lombok.Data;

import java.util.Date;
import java.util.Set;

@Data
public class PatientDto {
    private Long id;
    private String cf;
    private String name;
    private String surname;
    private Date birthday;
    private Date registrationdate;

}
