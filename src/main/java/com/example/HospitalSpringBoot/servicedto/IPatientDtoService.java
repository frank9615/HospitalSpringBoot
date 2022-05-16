package com.example.HospitalSpringBoot.servicedto;

import com.example.HospitalSpringBoot.dtos.PatientDto;
import com.example.HospitalSpringBoot.entities.Patient;

import java.util.List;

public interface IPatientDtoService {
    PatientDto findByCF(String cf);
    PatientDto findById(Long id);
    List<PatientDto> getAll();
    List<PatientDto> getPatientAssignedToDoctor_Id(Long id);
}
