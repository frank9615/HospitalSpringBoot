package com.example.HospitalSpringBoot.controllers;

import com.example.HospitalSpringBoot.dtos.TriageDto;
import com.example.HospitalSpringBoot.dtos.TriageUpdateDto;
import com.example.HospitalSpringBoot.dtos.UserDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

public interface TriageApi {
    @GetMapping(produces = "application/json")
    @Operation(summary ="Restituisce la lista dei triages", description = "Restituisce la lista dei triages", tags = "TriageApi")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved list of triages", content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = TriageDto.class)))),
            @ApiResponse(responseCode = "401", description = "You are not authorized to view the resource", content = @Content(mediaType = "application/json", schema = @Schema(implementation = AuthenticationException.class))),
            @ApiResponse(responseCode = "404", description = "The resource you were trying to reach is not found",  content = @Content(mediaType = "application/json", schema = @Schema(implementation = NotFoundException.class))),
            @ApiResponse(responseCode = "403", description = "Accessing the resource you were trying to reach is forbidden", content = @Content(mediaType = "application/json", schema = @Schema(implementation = AuthenticationException.class)))
    })
    public ResponseEntity<List<TriageDto>> getTriages();

    @GetMapping(value = "/search/id/{id}", produces = "application/json")
    @Operation(summary ="Restituisce il triage cercato", description = "Restituisce il triage cercato", tags = "TriageApi")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved list of triages", content = @Content(mediaType = "application/json", schema = @Schema(implementation = TriageDto.class))),
            @ApiResponse(responseCode = "401", description = "You are not authorized to view the resource", content = @Content(mediaType = "application/json", schema = @Schema(implementation = AuthenticationException.class))),
            @ApiResponse(responseCode = "404", description = "The resource you were trying to reach is not found",  content = @Content(mediaType = "application/json", schema = @Schema(implementation = NotFoundException.class))),
            @ApiResponse(responseCode = "403", description = "Accessing the resource you were trying to reach is forbidden", content = @Content(mediaType = "application/json", schema = @Schema(implementation = AuthenticationException.class)))
    })
    public ResponseEntity<TriageDto> findById(@PathVariable("id") Long id);

    @GetMapping(value = "/of/{type}/{id}",produces = "application/json")
    @Operation(summary ="Restituisce la lista dei triages dell'utente con il ruolo type e l'id scelto", description = "Restituisce la lista dei triages dell'utente con il ruolo type e l'id scelto", tags = "TriageApi")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved list of triages", content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = TriageDto.class)))),
            @ApiResponse(responseCode = "401", description = "You are not authorized to view the resource", content = @Content(mediaType = "application/json", schema = @Schema(implementation = AuthenticationException.class))),
            @ApiResponse(responseCode = "404", description = "The resource you were trying to reach is not found",  content = @Content(mediaType = "application/json", schema = @Schema(implementation = NotFoundException.class))),
            @ApiResponse(responseCode = "403", description = "Accessing the resource you were trying to reach is forbidden", content = @Content(mediaType = "application/json", schema = @Schema(implementation = AuthenticationException.class)))
    })
    public ResponseEntity<List<TriageDto>> getPatients(@PathVariable("type") String type, @PathVariable("id") Long id);


    @PostMapping(value = "/new")
    @Operation(summary ="Crea un nuovo Paziente", description = "Crea un nuovo Paziente", tags = "TriageApi")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved list of triages", content = @Content(mediaType = "application/json", schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "401", description = "You are not authorized to view the resource", content = @Content(mediaType = "application/json", schema = @Schema(implementation = AuthenticationException.class))),
            @ApiResponse(responseCode = "404", description = "The resource you were trying to reach is not found",  content = @Content(mediaType = "application/json", schema = @Schema(implementation = NotFoundException.class))),
            @ApiResponse(responseCode = "403", description = "Accessing the resource you were trying to reach is forbidden", content = @Content(mediaType = "application/json", schema = @Schema(implementation = AuthenticationException.class)))
    })
    public ResponseEntity<String> createPatient(@Valid @RequestBody TriageDto triagedto , BindingResult bindingResult);


    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    @Operation(summary ="Aggiorna un Paziente", description = "Aggiorna un Paziente", tags = "TriageApi")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved list of triages", content = @Content(mediaType = "application/json", schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "401", description = "You are not authorized to view the resource", content = @Content(mediaType = "application/json", schema = @Schema(implementation = AuthenticationException.class))),
            @ApiResponse(responseCode = "404", description = "The resource you were trying to reach is not found",  content = @Content(mediaType = "application/json", schema = @Schema(implementation = NotFoundException.class))),
            @ApiResponse(responseCode = "403", description = "Accessing the resource you were trying to reach is forbidden", content = @Content(mediaType = "application/json", schema = @Schema(implementation = AuthenticationException.class)))
    })
    public ResponseEntity<String> updatePatient(@Valid @RequestBody TriageUpdateDto triageupdateDto, BindingResult bindingResult);



    @DeleteMapping(value = "/delete/id/{id}", produces = "application/json")
    @Operation(summary ="Restituisce la lista dei triages", description = "Restituisce la lista dei triages", tags = "TriageApi")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved list of triages", content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = TriageDto.class)))),
            @ApiResponse(responseCode = "401", description = "You are not authorized to view the resource", content = @Content(mediaType = "application/json", schema = @Schema(implementation = AuthenticationException.class))),
            @ApiResponse(responseCode = "404", description = "The resource you were trying to reach is not found",  content = @Content(mediaType = "application/json", schema = @Schema(implementation = NotFoundException.class))),
            @ApiResponse(responseCode = "403", description = "Accessing the resource you were trying to reach is forbidden", content = @Content(mediaType = "application/json", schema = @Schema(implementation = AuthenticationException.class)))
    })
    public ResponseEntity<?> deletePatient(@PathVariable("id") Long id);

}
