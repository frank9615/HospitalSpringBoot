package com.example.HospitalSpringBoot;

import com.example.HospitalSpringBoot.dtos.PatientDto;
import com.example.HospitalSpringBoot.dtos.TriageDto;
import com.example.HospitalSpringBoot.entities.Patient;
import com.example.HospitalSpringBoot.entities.Triage;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper(){
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setSkipNullEnabled(true);
        modelMapper.addMappings(triageMapping);
        modelMapper.addMappings(patientMapping);
        return modelMapper;
    }

    PropertyMap<Triage, TriageDto> triageMapping = new PropertyMap<Triage, TriageDto>() {
        @Override
        protected void configure() {

        }
    };

    PropertyMap<Patient, PatientDto> patientMapping = new PropertyMap<Patient, PatientDto>() {
        @Override
        protected void configure() {
            // Sistemare il mapping guardando questo tutorial
            // -> https://www.baeldung.com/java-modelmapper
            //map().setRegistrationdate(source.getRegistrationdate());
        }
    };


}
