package com.example.HospitalSpringBoot.services;

import com.example.HospitalSpringBoot.dtos.PatientDto;
import com.example.HospitalSpringBoot.entities.Patient;

import java.util.List;
import java.util.Optional;

public interface IPatientService {
    PatientDto findByCF(String cf);
    PatientDto findById(Long id);
    Patient findById2(Long id);
    void save(Patient patient);
    void update(Patient patient);
    void delete(Patient patient);
    List<PatientDto> getAll();
    List<PatientDto> getPatientAssignedToDoctor_Id(Long id);
}
