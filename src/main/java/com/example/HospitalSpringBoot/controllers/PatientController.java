package com.example.HospitalSpringBoot.controllers;


import com.example.HospitalSpringBoot.enums.services.IPatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PatientController {

    @Autowired
    private IPatientService patientService;
}
