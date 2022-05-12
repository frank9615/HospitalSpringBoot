package com.example.HospitalSpringBoot.services.impl;

import com.example.HospitalSpringBoot.dtos.PatientDto;
import com.example.HospitalSpringBoot.dtos.TriageDto;
import com.example.HospitalSpringBoot.entities.Triage;
import com.example.HospitalSpringBoot.services.ITriageService;
import com.example.HospitalSpringBoot.repositories.TriageRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@Service
public class TriageService implements ITriageService {

    @Autowired
    private TriageRepository triageRepository;



    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Optional<Triage> findById(Long id) {
        return this.triageRepository.findById(id);
    }

    @Override
    public TriageDto findById2(Long id) {
        return modelMapper.map(this.triageRepository.findById(id).get(), TriageDto.class);
    }

    @Override
    public void save(Triage triage) {
        this.triageRepository.save(triage);
    }

    @Override
    public void update(Triage triage) {
        this.triageRepository.save(triage);
    }

    @Override
    public void delete(Triage triage) {
        this.triageRepository.delete(triage);
    }

    @Override
    public List<TriageDto> getByDoctorId(Long id) {
        return this.toList(StreamSupport.stream(this.triageRepository.findByDoctor_IdIs(id).spliterator(), false));
    }

    @Override
    public List<Triage> getByDoctorId2(Long id) {
        return this.triageRepository.findByDoctor_IdIs(id);
    }

    @Override
    public List<TriageDto> getByPatientId(Long id) {
        return this.toList(StreamSupport.stream(this.triageRepository.findByPatient_IdIs(id).spliterator(), false));
    }

    @Override
    public List<TriageDto> getByOperatorId(Long id) {
        return this.toList(StreamSupport.stream(this.triageRepository.findByOperator_IdIs(id).spliterator(), false));
    }

    @Override
    public List<TriageDto> getAll() {
        return this.toList(StreamSupport.stream(this.triageRepository.findAll().spliterator(), false));
    }

    private List<TriageDto> toList(Stream<Triage> triages){
        return triages.map(source -> modelMapper.map(source, TriageDto.class))
                .collect(Collectors.toList());

    }
}
