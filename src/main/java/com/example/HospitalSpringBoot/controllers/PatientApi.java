package com.example.HospitalSpringBoot.controllers;

import com.example.HospitalSpringBoot.dtos.PatientDto;
import com.example.HospitalSpringBoot.dtos.TriageDto;
import com.example.HospitalSpringBoot.entities.Patient;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.SneakyThrows;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

public interface PatientApi {
    @GetMapping(produces = "application/json")
    @Operation(summary ="Restituisce la lista dei pazienti", description = "Restituisce la lista dei pazienti", tags = "PatientApi")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved list of patients", content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = PatientDto.class)))),
            @ApiResponse(responseCode = "401", description = "You are not authorized to view the resource", content = @Content(mediaType = "application/json", schema = @Schema(implementation = AuthenticationException.class))),
            @ApiResponse(responseCode = "404", description = "The resource you were trying to reach is not found",  content = @Content(mediaType = "application/json", schema = @Schema(implementation = NotFoundException.class))),
            @ApiResponse(responseCode = "403", description = "Accessing the resource you were trying to reach is forbidden", content = @Content(mediaType = "application/json", schema = @Schema(implementation = AuthenticationException.class)))
    })
    public ResponseEntity<List<PatientDto>> getPatients();

    @GetMapping(value = "/search/id/{id}", produces = "application/json")
    @Operation(summary ="Restituisce il paziente cercato con l'id selezionato", description = "Restituisce il paziente cercato con l'id selezionato", tags = "PatientApi")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved patient with id selected", content = @Content(mediaType = "application/json", schema = @Schema(implementation = PatientDto.class))),
            @ApiResponse(responseCode = "401", description = "You are not authorized to view the resource", content = @Content(mediaType = "application/json", schema = @Schema(implementation = AuthenticationException.class))),
            @ApiResponse(responseCode = "404", description = "The resource you were trying to reach is not found",  content = @Content(mediaType = "application/json", schema = @Schema(implementation = NotFoundException.class))),
            @ApiResponse(responseCode = "403", description = "Accessing the resource you were trying to reach is forbidden", content = @Content(mediaType = "application/json", schema = @Schema(implementation = AuthenticationException.class)))
    })
    public ResponseEntity<PatientDto> findById(@PathVariable("id") Long id);

    @GetMapping(value = "/search/cf/{cf}", produces = "application/json")
    @Operation(summary ="Restituisce il paziente cercato con il codice fiscale selezionato", description = "Restituisce il paziente cercato con il codice fiscale selezionato", tags = "PatientApi")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved patient with cf selected", content = @Content(mediaType = "application/json", schema = @Schema(implementation = PatientDto.class))),
            @ApiResponse(responseCode = "401", description = "You are not authorized to view the resource", content = @Content(mediaType = "application/json", schema = @Schema(implementation = AuthenticationException.class))),
            @ApiResponse(responseCode = "404", description = "The resource you were trying to reach is not found",  content = @Content(mediaType = "application/json", schema = @Schema(implementation = NotFoundException.class))),
            @ApiResponse(responseCode = "403", description = "Accessing the resource you were trying to reach is forbidden", content = @Content(mediaType = "application/json", schema = @Schema(implementation = AuthenticationException.class)))
    })
    public ResponseEntity<PatientDto> findById(@PathVariable("cf") String cf);


    @PostMapping(value = "/new")
    @Operation(summary ="Crea un nuovo paziente", description = "Crea un nuovo paziente", tags = "PatientApi")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully created new patient", content = @Content(mediaType = "application/json", schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "401", description = "You are not authorized to view the resource", content = @Content(mediaType = "application/json", schema = @Schema(implementation = AuthenticationException.class))),
            @ApiResponse(responseCode = "404", description = "The resource you were trying to reach is not found",  content = @Content(mediaType = "application/json", schema = @Schema(implementation = NotFoundException.class))),
            @ApiResponse(responseCode = "403", description = "Accessing the resource you were trying to reach is forbidden", content = @Content(mediaType = "application/json", schema = @Schema(implementation = AuthenticationException.class)))
    })
    public ResponseEntity<String> createPatient(@Valid @RequestBody Patient patient , BindingResult bindingResult);


    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    @Operation(summary ="Aggiorna un  paziente", description = "Aggiorna un  paziente", tags = "PatientApi")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully updated new patient", content = @Content(mediaType = "application/json", schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "401", description = "You are not authorized to view the resource", content = @Content(mediaType = "application/json", schema = @Schema(implementation = AuthenticationException.class))),
            @ApiResponse(responseCode = "404", description = "The resource you were trying to reach is not found",  content = @Content(mediaType = "application/json", schema = @Schema(implementation = NotFoundException.class))),
            @ApiResponse(responseCode = "403", description = "Accessing the resource you were trying to reach is forbidden", content = @Content(mediaType = "application/json", schema = @Schema(implementation = AuthenticationException.class)))
    })
    public ResponseEntity<String> updatePatient(@Valid @RequestBody Patient patient, BindingResult bindingResult);


    @DeleteMapping(value = "/delete/id/{id}", produces = "application/json")
    @Operation(summary ="Elimina un  paziente", description = "Elimina un  paziente", tags = "PatientApi")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully deleted  patient", content = @Content(mediaType = "application/json", schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "401", description = "You are not authorized to view the resource", content = @Content(mediaType = "application/json", schema = @Schema(implementation = AuthenticationException.class))),
            @ApiResponse(responseCode = "404", description = "The resource you were trying to reach is not found",  content = @Content(mediaType = "application/json", schema = @Schema(implementation = NotFoundException.class))),
            @ApiResponse(responseCode = "403", description = "Accessing the resource you were trying to reach is forbidden", content = @Content(mediaType = "application/json", schema = @Schema(implementation = AuthenticationException.class)))
    })
    public ResponseEntity<?> deletePatient(@PathVariable("id") Long id);

    //get list of patients from doctor_id

    @GetMapping(value = "/assigned/doctor/id/{id}", produces = "application/json")
    @Operation(summary ="Restituisce la lista dei pazienti associti al dottore con l'ide selezionato", description = "Restituisce la lista dei pazienti associti al dottore con l'ide selezionato", tags = "PatientApi")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved list of patients", content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = PatientDto.class)))),
            @ApiResponse(responseCode = "401", description = "You are not authorized to view the resource", content = @Content(mediaType = "application/json", schema = @Schema(implementation = AuthenticationException.class))),
            @ApiResponse(responseCode = "404", description = "The resource you were trying to reach is not found",  content = @Content(mediaType = "application/json", schema = @Schema(implementation = NotFoundException.class))),
            @ApiResponse(responseCode = "403", description = "Accessing the resource you were trying to reach is forbidden", content = @Content(mediaType = "application/json", schema = @Schema(implementation = AuthenticationException.class)))
    })
    public ResponseEntity<List<PatientDto>> getPatientsAssignedToDoctorId(@PathVariable("id") Long id);
}
