package com.example.HospitalSpringBoot.services.impl;

import com.example.HospitalSpringBoot.entities.Utente;
import com.example.HospitalSpringBoot.repositories.UtenteRepository;
import com.example.HospitalSpringBoot.services.IUtenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service

public class UtenteService implements IUtenteService {
    @Autowired
    UtenteRepository utenteRepository;

    @Override
    public List<Utente> getAll() {
        return (List<Utente>)this.utenteRepository.findAll();
    }

    @Override
    public Optional<Utente> getById(Long id) {
        return this.utenteRepository.findById(id);
    }

    @Override
    public void save(Utente utente) {
        this.utenteRepository.save(utente);
    }

    @Override
    public void delete(Utente utente) {
        this.utenteRepository.delete(utente);
    }

    @Override
    public Utente getByUsername(String username) {
        return this.utenteRepository.findByUsername(username);
    }


}
