package com.example.HospitalSpringBoot.services.impl;

import com.example.HospitalSpringBoot.dtos.PatientDto;
import com.example.HospitalSpringBoot.entities.Patient;
import com.example.HospitalSpringBoot.repositories.PatientRepository;
import com.example.HospitalSpringBoot.services.IPatientService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class PatientService implements IPatientService {

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private ModelMapper modelMapper;

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
    public List<PatientDto> getAll() {
        List<PatientDto> patients = StreamSupport.stream(this.patientRepository.findAll().spliterator(), false)
                .map(source -> modelMapper.map(source, PatientDto.class))
                .collect(Collectors.toList());
        return patients;
    }
}
