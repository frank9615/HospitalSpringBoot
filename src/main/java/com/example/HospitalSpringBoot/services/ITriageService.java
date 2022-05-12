package com.example.HospitalSpringBoot.services;

import com.example.HospitalSpringBoot.dtos.TriageDto;
import com.example.HospitalSpringBoot.entities.Triage;

import java.util.List;
import java.util.Optional;

public interface ITriageService {
    Optional<Triage> findById(Long id);
    TriageDto findById2(Long id);
    void save(Triage triage);
    void update(Triage triage);
    void delete(Triage triage);
    List<TriageDto> getByDoctorId(Long id);
    List<Triage> getByDoctorId2(Long id);
    List<TriageDto> getByPatientId(Long id);
    List<TriageDto> getByOperatorId(Long id);
    List<TriageDto> getAll();
}
