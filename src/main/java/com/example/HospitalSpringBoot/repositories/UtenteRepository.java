package com.example.HospitalSpringBoot.repositories;

import com.example.HospitalSpringBoot.entities.Utente;
import org.springframework.data.repository.CrudRepository;

public interface UtenteRepository extends CrudRepository<Utente, Long> {
    public Utente findByUsername(String username);
}
