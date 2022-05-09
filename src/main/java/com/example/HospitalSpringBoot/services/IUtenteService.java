package com.example.HospitalSpringBoot.services;

import com.example.HospitalSpringBoot.entities.Utente;

import java.util.List;
import java.util.Optional;


public interface IUtenteService {
    public List<Utente> getAll();
    public Optional<Utente> getById(Long id);
    public void save(Utente utente);
    public void delete(Utente utente);
}
