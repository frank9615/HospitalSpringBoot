package com.example.HospitalSpringBoot.controllers;

import com.example.HospitalSpringBoot.dtos.UserDto;
import com.example.HospitalSpringBoot.entities.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.SneakyThrows;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.util.List;

public interface UserApi {
    @GetMapping(produces = "application/json")
    @Operation(summary ="Restituisce la lista degli utenti", description = "Restituisce la lista degli utenti", tags = "UserApi")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved list of users", content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = UserDto.class)))),
            @ApiResponse(responseCode = "401", description = "You are not authorized to view the resource", content = @Content(mediaType = "application/json", schema = @Schema(implementation = AuthenticationException.class))),
            @ApiResponse(responseCode = "404", description = "The resource you were trying to reach is not found",  content = @Content(mediaType = "application/json", schema = @Schema(implementation = NotFoundException.class))),
            @ApiResponse(responseCode = "403", description = "Accessing the resource you were trying to reach is forbidden", content = @Content(mediaType = "application/json", schema = @Schema(implementation = AuthenticationException.class)))
    })
    ResponseEntity<List<UserDto>> getUsers();

    @GetMapping(value = "/list/role/{role}" , produces = "application/json")
    @Operation(summary ="Restituisce la lista degli utenti con il ruolo definito", description = "Restituisce la lista degli utenti con il ruolo definito", tags = "UserApi")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved list of users with specific role", content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = UserDto.class)))),
            @ApiResponse(responseCode = "401", description = "You are not authorized to view the resource", content = @Content(mediaType = "application/json", schema = @Schema(implementation = AuthenticationException.class))),
            @ApiResponse(responseCode = "404", description = "The resource you were trying to reach is not found",  content = @Content(mediaType = "application/json", schema = @Schema(implementation = NotFoundException.class))),
            @ApiResponse(responseCode = "403", description = "Accessing the resource you were trying to reach is forbidden", content = @Content(mediaType = "application/json", schema = @Schema(implementation = AuthenticationException.class)))
    })
    ResponseEntity<List<UserDto>> getUsersbyRole(@PathVariable("role") String role);

    @PostMapping(value = "/new")
    @Operation(summary ="Crea un nuovo utente", description = "Crea un nuovo utente", tags = "UserApi")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully created new user", content = @Content(mediaType = "application/json", schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "401", description = "You are not authorized to view the resource", content = @Content(mediaType = "application/json", schema = @Schema(implementation = AuthenticationException.class))),
            @ApiResponse(responseCode = "404", description = "The resource you were trying to reach is not found",  content = @Content(mediaType = "application/json", schema = @Schema(implementation = NotFoundException.class))),
            @ApiResponse(responseCode = "403", description = "Accessing the resource you were trying to reach is forbidden", content = @Content(mediaType = "application/json", schema = @Schema(implementation = AuthenticationException.class)))
    })
    ResponseEntity<String> createUser(@Valid @RequestBody User user , BindingResult bindingResult);

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    @Operation(summary ="Aggiorna un utente", description = "Aggiorna un utente", tags = "UserApi")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully updated new user", content = @Content(mediaType = "application/json", schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "401", description = "You are not authorized to view the resource", content = @Content(mediaType = "application/json", schema = @Schema(implementation = AuthenticationException.class))),
            @ApiResponse(responseCode = "404", description = "The resource you were trying to reach is not found",  content = @Content(mediaType = "application/json", schema = @Schema(implementation = NotFoundException.class))),
            @ApiResponse(responseCode = "403", description = "Accessing the resource you were trying to reach is forbidden", content = @Content(mediaType = "application/json", schema = @Schema(implementation = AuthenticationException.class)))
    })
    ResponseEntity<String> updateUser(@Valid @RequestBody User user, BindingResult bindingResult);

    @DeleteMapping(value = "/delete/id/{id}", produces = "application/json")
    @Operation(summary ="Elimina un utente", description = "Elimina un utente", tags = "UserApi")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully deleted user with id specificated", content = @Content(mediaType = "application/json", schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "401", description = "You are not authorized to view the resource", content = @Content(mediaType = "application/json", schema = @Schema(implementation = AuthenticationException.class))),
            @ApiResponse(responseCode = "404", description = "The resource you were trying to reach is not found",  content = @Content(mediaType = "application/json", schema = @Schema(implementation = NotFoundException.class))),
            @ApiResponse(responseCode = "403", description = "Accessing the resource you were trying to reach is forbidden", content = @Content(mediaType = "application/json", schema = @Schema(implementation = AuthenticationException.class)))
    })
    ResponseEntity<?> deleteUser(@PathVariable("id") Long id);

    @PostMapping(value = "/filter/{pagenum}/{el}", produces = "application/json")
    @Operation(summary ="Restituisce la lista degli utenti con i filtri specificati", description = "Restituisce la lista degli utenti con i filtri specificati", tags = "UserApi")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved list of users with specific filters", content = @Content(mediaType = "application/json", schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "401", description = "You are not authorized to view the resource", content = @Content(mediaType = "application/json", schema = @Schema(implementation = AuthenticationException.class))),
            @ApiResponse(responseCode = "404", description = "The resource you were trying to reach is not found",  content = @Content(mediaType = "application/json", schema = @Schema(implementation = NotFoundException.class))),
            @ApiResponse(responseCode = "403", description = "Accessing the resource you were trying to reach is forbidden", content = @Content(mediaType = "application/json", schema = @Schema(implementation = AuthenticationException.class)))
    })
    ResponseEntity<Page<UserDto>> specification(
            @RequestBody SearchCriteria searchCriteria,
            @PathVariable("pagenum") String pagenum,
            @PathVariable("el") String el);

}
