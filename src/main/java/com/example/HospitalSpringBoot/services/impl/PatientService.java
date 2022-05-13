package com.example.HospitalSpringBoot.services.impl;

import com.example.HospitalSpringBoot.dtos.PatientDto;
import com.example.HospitalSpringBoot.entities.Patient;
import com.example.HospitalSpringBoot.entities.Triage;
import com.example.HospitalSpringBoot.repositories.PatientRepository;
import com.example.HospitalSpringBoot.services.IPatientService;
import com.example.HospitalSpringBoot.services.ITriageService;
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

    @Autowired
    private ITriageService triageService;

    @Override
    public PatientDto findByCF(String cf) {
        return modelMapper.map(this.patientRepository.findByCf(cf), PatientDto.class);
    }

    @Override
    public PatientDto findById(Long id) {
        return modelMapper.map(this.patientRepository.findById(id).get(), PatientDto.class);
    }

    @Override
    public Patient findById2(Long id) {
        return this.patientRepository.findById(id).get();
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
    @Override
    public List<PatientDto> getPatientAssignedToDoctor_Id(Long id){
        List<Triage> triagesAssignedToDoctorId = this.triageService.getByDoctorId2(id);
        List<PatientDto> patients = triagesAssignedToDoctorId.stream().map(source -> modelMapper.map(source.getPatient(), PatientDto.class)).collect(Collectors.toList());
        return patients; 
    }
}
