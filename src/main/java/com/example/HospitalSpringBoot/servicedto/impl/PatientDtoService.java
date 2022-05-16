package com.example.HospitalSpringBoot.servicedto.impl;

import com.example.HospitalSpringBoot.dtos.PatientDto;
import com.example.HospitalSpringBoot.entities.Patient;
import com.example.HospitalSpringBoot.entities.Triage;
import com.example.HospitalSpringBoot.repositories.PatientRepository;
import com.example.HospitalSpringBoot.servicedto.IPatientDtoService;
import com.example.HospitalSpringBoot.services.IPatientService;
import com.example.HospitalSpringBoot.services.ITriageService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class PatientDtoService implements IPatientDtoService {

    @Autowired
    private IPatientService patientService;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ITriageService triageService;

    @Override
    public PatientDto findByCF(String cf) {
        return modelMapper.map(this.patientService.findByCF(cf), PatientDto.class);
    }

    @Override
    public PatientDto findById(Long id) {
        return modelMapper.map(this.patientService.findById(id), PatientDto.class);
    }

    @Override
    public List<PatientDto> getAll() {
        return this.patientService.getAll().stream().map(source -> modelMapper.map(source, PatientDto.class)).collect(Collectors.toList());
    }
    @Override
    public List<PatientDto> getPatientAssignedToDoctor_Id(Long id){
        return this.patientService.getPatientAssignedToDoctor_Id(id).stream().map(source -> modelMapper.map(source, PatientDto.class)).collect(Collectors.toList());
    }
}
