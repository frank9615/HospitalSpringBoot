package com.example.HospitalSpringBoot.services.impl;

import com.example.HospitalSpringBoot.entities.Patient;
import com.example.HospitalSpringBoot.repositories.PatientRepository;
import com.example.HospitalSpringBoot.services.IPatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatientService implements IPatientService {

    @Autowired
    private PatientRepository patientRepository;

    @Override
    public Patient findByCF(String cf) {
        return this.patientRepository.findByCf(cf);
    }

    @Override
    public Optional<Patient> findById(Long id) {
        return this.patientRepository.findById(id);
    }

    @Override
    public void save(Patient patient) {
        this.patientRepository.save(patient);
    }

    @Override
    public void update(Patient patient) {
        this.patientRepository.save(patient);
    }

    @Override
    public void delete(Patient patient) {
        this.patientRepository.delete(patient);
    }

    @Override
    public List<Patient> getAll() {
        return (List<Patient>)this.patientRepository.findAll();
    }
}
