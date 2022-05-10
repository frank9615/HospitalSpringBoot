package com.example.HospitalSpringBoot.repositories;

import com.example.HospitalSpringBoot.entities.Patient;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface PatientRepository extends PagingAndSortingRepository<Patient, Long> {
    public Patient findByCf(String cf);
}
