package com.example.HospitalSpringBoot.controllers;

import com.example.HospitalSpringBoot.dtos.PatientDto;
import com.example.HospitalSpringBoot.entities.Patient;
import com.example.HospitalSpringBoot.entities.User;
import com.example.HospitalSpringBoot.enums.Role;
import com.example.HospitalSpringBoot.services.IUserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.SneakyThrows;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("api/users")
@Log
public class UserController {

    @Autowired
    IUserService userService;

    /* Le operazioni da fare sono cercare un utente
    * cercare un utente
    * salvare un utente
    * modificare un utente
    * ottenere la lista di tutti gli utenti
    */

    @GetMapping(produces = "application/json")
    @SneakyThrows
    public ResponseEntity<List<User>> getPatients(){
        log.info("*** Ottengo la lista degli utenti ***");
        List<User> users = this.userService.getAll();
        if(users.isEmpty()){
            String errMsg = "Non esiste nessun utente";
            log.warning(errMsg);
            throw new Exception(errMsg);
        }
        return new ResponseEntity<List<User>>(users, HttpStatus.OK);
    }

    @GetMapping(value = "/search/id/{id}", produces = "application/json")
    @SneakyThrows
    public ResponseEntity<User> findById(@PathVariable("id") Long id){
        log.info("****** Ottengo il paziente con l\'id : " + id + " *******");
        User user = this.userService.getById(id).get();

        if(user == null){
            String errMsg = String.format("L\'utente con l\'id: %s non è stato trovato", id);
            log.warning(errMsg);
            throw new Exception(errMsg);
        }
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }

    @GetMapping(value = "/list/role/{role}" , produces = "application/json")
    @SneakyThrows
    public ResponseEntity<List<User>> getPatients(@PathVariable("role") Role role){
        log.info("*** Ottengo la lista degli utenti con ruolo "+role+ " ****");
        List<User> users = this.userService.findAllByRole(role);
        if(users.isEmpty()){
            String errMsg = String.format("Non esiste nessun utente con il ruolo %s", role);
            log.warning(errMsg);
            throw new Exception(errMsg);
        }
        return new ResponseEntity<List<User>>(users, HttpStatus.OK);
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
        this.userService.save(user);
        return new ResponseEntity<String>("Insetimento Utente eseguito con successo", HttpStatus.CREATED);
    }

    @SneakyThrows
    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public ResponseEntity<String> updateUser(@Valid @RequestBody User user, BindingResult bindingResult){
        log.info("******* Update Patient " +user.getUsername());
        if(bindingResult.hasErrors()){
            String errMsg = bindingResult.getFieldError().toString();
            log.warning(errMsg);
            throw new Exception(errMsg);
        }
        User _user = this.userService.getById(user.getId()).get();
        if(_user == null){
            String errMsg = String.format("Utente con id: %s non esiste", user.getId());
            log.warning(errMsg);
            throw new Exception(errMsg);
        }
        this.userService.save(user);
        return new ResponseEntity<String>("Modifica Utente eseguito con successo", HttpStatus.CREATED);
    }

    @SneakyThrows
    @DeleteMapping(value = "/delete/id/{id}", produces = "application/json")
    public ResponseEntity<?> deleteUser(@PathVariable("id") Long id){
        log.info("********* Delete Utente with id " + id);
        User user = this.userService.getById(id).get();
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

}
