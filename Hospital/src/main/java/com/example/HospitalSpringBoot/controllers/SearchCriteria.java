package com.example.HospitalSpringBoot.controllers;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SearchCriteria {
    private String username;
    private String name;
    private String surname;

}
