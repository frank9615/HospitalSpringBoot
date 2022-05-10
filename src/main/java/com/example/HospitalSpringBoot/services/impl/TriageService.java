package com.example.HospitalSpringBoot.services.impl;

import com.example.HospitalSpringBoot.entities.Triage;
import com.example.HospitalSpringBoot.services.ITriageService;
import com.example.HospitalSpringBoot.repositories.TriageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TriageService implements ITriageService {

    @Autowired
    private TriageRepository triageRepository;

    @Override
    public Optional<Triage> findById(Long id) {
        return this.triageRepository.findById(id);
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
    public List<Triage> getByDoctorId(Long id) {
        return this.triageRepository.findByDoctor_IdIs(id);
    }

    @Override
    public List<Triage> getByPatientId(Long id) {
        return this.triageRepository.findByPatient_IdIs(id);
    }

    @Override
    public List<Triage> getByOperatorId(Long id) {
        return this.triageRepository.findByOperator_IdIs(id);
    }

    @Override
    public List<Triage> getAll() {
        return (List<Triage>) this.triageRepository.findAll();
    }
}
