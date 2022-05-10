package com.example.HospitalSpringBoot.controllers;


import com.example.HospitalSpringBoot.services.ITriageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TriageController {

    @Autowired
    private ITriageService triageService;
}
