package com.example.HospitalSpringBoot;

import com.example.HospitalSpringBoot.dtos.TriageDto;
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
        return modelMapper;
    }

    PropertyMap<Triage, TriageDto> triageMapping = new PropertyMap<Triage, TriageDto>() {
        @Override
        protected void configure() {

        }
    };
}
