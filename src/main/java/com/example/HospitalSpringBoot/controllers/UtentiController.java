package com.example.HospitalSpringBoot.controllers;

import com.example.HospitalSpringBoot.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/users")
public class UtentiController {

    @Autowired
    IUserService userService;

    /* Le operazioni da fare sono cercare un utente
    * cercare un utente
    * salvare un utente
    * modificare un utente
    * ottenere la lista di tutti gli utenti
    */
}
