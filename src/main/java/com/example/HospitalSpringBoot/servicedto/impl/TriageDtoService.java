package com.example.HospitalSpringBoot.servicedto.impl;

import com.example.HospitalSpringBoot.dtos.TriageDto;
import com.example.HospitalSpringBoot.entities.Triage;
import com.example.HospitalSpringBoot.repositories.TriageRepository;
import com.example.HospitalSpringBoot.servicedto.ITriageDtoService;
import com.example.HospitalSpringBoot.services.ITriageService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@Service
public class TriageDtoService implements ITriageDtoService {
    @Autowired
    private ITriageService triageService;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public TriageDto findById(Long id) {
        return modelMapper.map(this.triageService.findById(id), TriageDto.class);
    }

    @Override
    public List<TriageDto> getByDoctorId(Long id) {
        return this.triageService.getByDoctorId(id)
                .stream()
                .map(source -> modelMapper.map(source, TriageDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<TriageDto> getByPatientId(Long id) {
        return this.triageService.getByPatientId(id)
                .stream()
                .map(source -> modelMapper.map(source, TriageDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<TriageDto> getByOperatorId(Long id) {
        return this.triageService.getByOperatorId(id)
                .stream()
                .map(source -> modelMapper.map(source, TriageDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<TriageDto> getAll() {
        return this.triageService.getAll()
                .stream()
                .map(source -> modelMapper.map(source, TriageDto.class))
                .collect(Collectors.toList());
    }
}
