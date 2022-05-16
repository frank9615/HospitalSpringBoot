package com.example.HospitalSpringBoot.services;

import com.example.HospitalSpringBoot.dtos.PatientDto;
import com.example.HospitalSpringBoot.entities.Patient;

import java.util.List;
import java.util.Optional;

public interface IPatientService {
    Patient findByCF(String cf);
    Patient findById(Long id);
    void save(Patient patient);
    void update(Patient patient);
    void delete(Patient patient);
    List<Patient> getAll();
    List<Patient> getPatientAssignedToDoctor_Id(Long id);
}
