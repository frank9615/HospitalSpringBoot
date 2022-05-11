package com.example.HospitalSpringBoot;

import com.example.HospitalSpringBoot.dtos.PatientDto;
import com.example.HospitalSpringBoot.dtos.TriageDto;
import com.example.HospitalSpringBoot.entities.*;
import com.example.HospitalSpringBoot.services.IPatientService;
import com.example.HospitalSpringBoot.services.IUserService;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    @Autowired
    private IUserService userService;

    @Autowired
    private IPatientService patientService;

    @Bean
    public ModelMapper modelMapper(){
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setSkipNullEnabled(true);
        modelMapper.addMappings(triageMapping);
        modelMapper.addMappings(patientMapping);
        modelMapper.addMappings(triagereverseMapping);
        return modelMapper;
    }

    PropertyMap<Triage, TriageDto> triageMapping = new PropertyMap<Triage, TriageDto>() {
        @Override
        protected void configure() {
            map().setDoctor_id(source.getDoctor().getId());
            map().setOperator_id(source.getOperator().getId());
            map().setPatient_id(source.getPatient().getId());
        }
    };

    PropertyMap<TriageDto, Triage> triagereverseMapping = new PropertyMap<TriageDto, Triage>() {
        @Override
        protected void configure() {
            map().setDoctor((Doctor) userService.getById(source.getDoctor_id()).get());
            map().setOperator((Operator) userService.getById(source.getOperator_id()).get());
            map().setPatient(patientService.findById2(source.getPatient_id()));
        }
    };

    PropertyMap<Patient, PatientDto> patientMapping = new PropertyMap<Patient, PatientDto>() {
        @Override
        protected void configure() {

        }
    };






}
