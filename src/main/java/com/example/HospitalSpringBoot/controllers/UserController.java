package com.example.HospitalSpringBoot.controllers;

import com.example.HospitalSpringBoot.dtos.UserDto;
import com.example.HospitalSpringBoot.entities.Admin;
import com.example.HospitalSpringBoot.entities.Doctor;
import com.example.HospitalSpringBoot.entities.Operator;
import com.example.HospitalSpringBoot.entities.User;
import com.example.HospitalSpringBoot.enums.Role;
import com.example.HospitalSpringBoot.services.IUserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.SneakyThrows;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

import java.util.List;
import java.util.Locale;


@RestController
@RequestMapping("api/users")
@Log
public class UserController {

    @Autowired
    IUserService userService;


    @PostMapping(value = "/filter/{pagenum}/{el}", produces = "application/json")
    @SneakyThrows
    public  ResponseEntity<Page<UserDto>> specification(
            @RequestBody SearchCriteria searchCriteria,
            @PathVariable("pagenum") String pagenum,
            @PathVariable("el") String el) {
        Pageable page = PageRequest.of(Integer.valueOf(pagenum), Integer.valueOf(el));

        Page<UserDto> users = userService.getAllSpecification(searchCriteria.getUsername(), searchCriteria.getName(), searchCriteria.getSurname(), page);
        if(users.isEmpty()){
            String errMsg = "Non esiste nessun utente con i filtri di ricerca selezionati";
            log.warning(errMsg);
            throw new Exception(errMsg);
        }
        return new ResponseEntity<Page<UserDto>>(users, HttpStatus.OK);
    }

    @GetMapping(produces = "application/json")
    @SneakyThrows
    public ResponseEntity<List<UserDto>> getUsers(){
        log.info("*** Ottengo la lista degli utenti ***");
        List<UserDto> users = this.userService.getAll();
        if(users.isEmpty()){
            String errMsg = "Non esiste nessun utente";
            log.warning(errMsg);
            throw new Exception(errMsg);
        }
        return new ResponseEntity<List<UserDto>>(users, HttpStatus.OK);
    }

    @GetMapping(value = "/search/id/{id}", produces = "application/json")
    @SneakyThrows
    public ResponseEntity<UserDto> findById(@PathVariable("id") Long id){
        log.info("****** Ottengo Utente con l\'id : " + id + " *******");
        UserDto userdto = this.userService.getById2(id);

        if(userdto == null){
            String errMsg = String.format("L\'utente con l\'id: %s non è stato trovato", id);
            log.warning(errMsg);
            throw new Exception(errMsg);
        }
        return new ResponseEntity<UserDto>(userdto, HttpStatus.OK);
    }

    @GetMapping(value = "/list/role/{role}" , produces = "application/json")
    @SneakyThrows
    public ResponseEntity<List<UserDto>> getUsersbyRole(@PathVariable("role") String role){
        log.info("*** Ottengo la lista degli utenti con ruolo "+ role + " ****");


        List<UserDto> users = this.userService.findAllByRole(Role.valueOf(role.toUpperCase()));
        if(users.isEmpty()){
            String errMsg = String.format("Non esiste nessun utente con il ruo1lo %s", role);
            log.warning(errMsg);
            throw new Exception(errMsg);
        }
        return new ResponseEntity<List<UserDto>>(users, HttpStatus.OK);
    }
    //add user
    @SneakyThrows
    @PostMapping(value = "/new")
    public ResponseEntity<String> createUser(@Valid @RequestBody User user , BindingResult bindingResult){
        log.info("******* New User " + user.getUsername());
        if(bindingResult.hasErrors()){
            String errMsg = bindingResult.getFieldError().toString();
            log.warning(errMsg);
            throw new Exception(errMsg);
        }
        log.info("Utente passato : " + user.toString());

        this.castAndSaveUser(user);

        return new ResponseEntity<String>("Insetimento Utente eseguito con successo", HttpStatus.CREATED);
    }

    @SneakyThrows
    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public ResponseEntity<String> updateUser(@Valid @RequestBody User user, BindingResult bindingResult){
        log.info("******* Update User " +user.getUsername());
        if(bindingResult.hasErrors()){
            String errMsg = bindingResult.getFieldError().toString();
            log.warning(errMsg);
            throw new Exception(errMsg);
        }
        log.info("Utente passato " + user.toString());
        User _user = this.userService.getById(user.getId());

        if(_user == null){
            String errMsg = String.format("Utente con id: %s non esiste", user.getId());
            log.warning(errMsg);
            throw new Exception(errMsg);
        }
        //cast to specific object
        this.castAndSaveUser(user);
        return new ResponseEntity<String>("Modifica Utente eseguito con successo", HttpStatus.CREATED);
    }

    @SneakyThrows
    @DeleteMapping(value = "/delete/id/{id}", produces = "application/json")
    public ResponseEntity<?> deleteUser(@PathVariable("id") Long id){
        log.info("********* Delete Utente with id " + id);
        User user = this.userService.getById(id);
        if(user == null){
            String errMsg = String.format("Utente con id: %s non esiste", id);
            log.warning(errMsg);
            throw new Exception(errMsg);
        }
        this.userService.delete(user);

        //Costruzione di un oggetto json
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode responseNode = mapper.createObjectNode();
        responseNode.put("code", HttpStatus.OK.toString());
        responseNode.put("message", "Eliminazione Utente " + id + " eseguita con successo" );
        return new ResponseEntity<>(responseNode, new HttpHeaders(), HttpStatus.OK);
    }

    @SneakyThrows
    private void castAndSaveUser(User user){
        switch (user.getRole()){
            case ADMIN:
                Admin admin = new Admin(user);
                this.userService.save(admin);
                break;
            case DOCTOR:
                Doctor doctor = new Doctor(user);
                this.userService.save(doctor);
                break;
            case OPERATOR:
                Operator operator  = new Operator(user);
                this.userService.save(operator);
                break;
            default:
                String errMsg = "Ruolo non valido";
                log.warning(errMsg);
                throw new Exception(errMsg);
        }
    }

}
