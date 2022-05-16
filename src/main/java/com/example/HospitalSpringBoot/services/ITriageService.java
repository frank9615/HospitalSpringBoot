package com.example.HospitalSpringBoot.services;

import com.example.HospitalSpringBoot.dtos.TriageDto;
import com.example.HospitalSpringBoot.entities.Triage;

import java.util.List;
import java.util.Optional;

public interface ITriageService {
    Triage findById(Long id);
    void save(Triage triage);
    void update(Triage triage);
    void delete(Triage triage);
    List<Triage> getByDoctorId(Long id);
    List<Triage> getByPatientId(Long id);
    List<Triage> getByOperatorId(Long id);
    List<Triage> getAll();

}
