package com.example.HospitalSpringBoot.repositories;

import com.example.HospitalSpringBoot.entities.Triage;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;
import java.util.Optional;

public interface TriageRepository extends PagingAndSortingRepository<Triage,Long> {


    public List<Triage> findByDoctor_IdIs(Long id);

    public List<Triage> findByOperator_IdIs(Long id);

    public List<Triage> findByPatient_IdIs(Long id);






}
