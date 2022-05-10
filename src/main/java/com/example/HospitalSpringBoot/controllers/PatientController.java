package com.example.HospitalSpringBoot.controllers;


import com.example.HospitalSpringBoot.services.IPatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/patients")
public class PatientController {

    @Autowired
    private IPatientService patientService;



}
