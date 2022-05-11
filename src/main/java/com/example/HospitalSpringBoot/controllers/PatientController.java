package com.example.HospitalSpringBoot.controllers;


import com.example.HospitalSpringBoot.dtos.PatientDto;
import com.example.HospitalSpringBoot.entities.Patient;
import com.example.HospitalSpringBoot.services.IPatientService;
import lombok.SneakyThrows;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/patients")
@Log
public class PatientController {

    @Autowired
    private IPatientService patientService;

    @GetMapping(produces = "application/json")
    @SneakyThrows
    public ResponseEntity<List<PatientDto>> getPatients(){
        log.info("*** Ottengo la lista dei pazienti ***");
        List<PatientDto> patients = patientService.getAll();
        if(patients.isEmpty()){
            String errMsg = "Non esiste nessun paziente";
            log.warning(errMsg);
            throw new Exception(errMsg);
        }
        return new ResponseEntity<List<PatientDto>>(patients, HttpStatus.OK);
    }



}
