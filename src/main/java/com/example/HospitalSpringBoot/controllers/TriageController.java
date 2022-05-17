package com.example.HospitalSpringBoot.controllers;


import com.example.HospitalSpringBoot.dtos.TriageDto;
import com.example.HospitalSpringBoot.dtos.TriageUpdateDto;
import com.example.HospitalSpringBoot.entities.*;
import com.example.HospitalSpringBoot.servicedto.ITriageDtoService;
import com.example.HospitalSpringBoot.services.IPatientService;
import com.example.HospitalSpringBoot.services.ITriageService;
import com.example.HospitalSpringBoot.services.IUserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.SneakyThrows;
import lombok.extern.java.Log;
import org.modelmapper.ModelMapper;
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
@RequestMapping("api/triages")
@Log
public class TriageController implements TriageApi {

    @Autowired
    private ITriageService triageService;

    @Autowired
    private IPatientService patientService;

    @Autowired
    private IUserService userService;

    @Autowired
    private ITriageDtoService triageDtoService;


    @Autowired
    private ModelMapper modelMapper;

    @GetMapping(produces = "application/json")
    @SneakyThrows
    public ResponseEntity<List<TriageDto>> getTriages(){
        log.info("*** Ottengo la lista dei triage ***");
        List<TriageDto> triages = triageDtoService.getAll();
        if(triages.isEmpty()){
            String errMsg = "Non esiste nessun triage";
            log.warning(errMsg);
            throw new Exception(errMsg);
        }
        return new ResponseEntity<List<TriageDto>>(triages, HttpStatus.OK);
    }

    @GetMapping(value = "/search/id/{id}", produces = "application/json")
    @SneakyThrows
    public ResponseEntity<TriageDto> findById(@PathVariable("id") Long id){
        log.info("****** Ottengo il triage con l\'id : " + id + " *******");
        TriageDto triageDto = this.triageDtoService.findById(id);

        if(triageDto == null){
            String errMsg = String.format("Il triage con l\'id: %s non è stato trovato", id);
            log.warning(errMsg);
            throw new Exception(errMsg);
        }
        return new ResponseEntity<TriageDto>(triageDto, HttpStatus.OK);
    }

    @GetMapping(value = "/of/{type}/{id}",produces = "application/json")
    @SneakyThrows
    public ResponseEntity<List<TriageDto>> getPatients(@PathVariable("type") String type, @PathVariable("id") Long id){
        log.info("*** Ottengo la lista dei triages associati all'utente:" +id +"***");
        List<TriageDto> triageDtos;
        switch(type){
            case "doctor":
                triageDtos = this.triageDtoService.getByDoctorId(id);
                break;
            case "operator":
                triageDtos = this.triageDtoService.getByOperatorId(id);
                break;
            case "patient":
                triageDtos = this.triageDtoService.getByPatientId(id);
                break;
            default:
                String errMsg = "Parametro type non corretto";
                log.warning(errMsg);
                throw new Exception(errMsg);
        }
        if(triageDtos.isEmpty()){
            String errMsg = "Non esiste nessun triage associato all'utente: " + id;
            log.warning(errMsg);
            throw new Exception(errMsg);
        }
        return new ResponseEntity<List<TriageDto>>(triageDtos, HttpStatus.OK);
    }

    @SneakyThrows
    @PostMapping(value = "/new")
    public ResponseEntity<String> createPatient(@Valid @RequestBody TriageDto triagedto , BindingResult bindingResult){
        log.info("******* New Triage associato al paziente: " + triagedto.getPatient_id());
        if(bindingResult.hasErrors()){
            String errMsg = bindingResult.getFieldError().toString();
            log.warning(errMsg);
            throw new Exception(errMsg);
        }
        triagedto.setTriageDate(new Date());
        Triage triage = this.modelMapper.map(triagedto, Triage.class);
        triage.setDoctor((Doctor)this.userService.getById(triagedto.getDoctor_id()));
        triage.setOperator((Operator) this.userService.getById(triagedto.getOperator_id()));
        triage.setPatient( this.patientService.findById(triagedto.getPatient_id()));
        this.triageService.save(triage);
        return new ResponseEntity<String>("Insetimento Triage eseguito con successo", HttpStatus.CREATED);
    }

    @SneakyThrows
    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public ResponseEntity<String> updatePatient(@Valid @RequestBody TriageUpdateDto triageupdateDto, BindingResult bindingResult){
        log.info("******* Update Triage " + triageupdateDto.getId());
        if(bindingResult.hasErrors()){
            String errMsg = bindingResult.getFieldError().toString();
            log.warning(errMsg);
            throw new Exception(errMsg);
        }
        log.info(triageupdateDto.toString());
        Triage triage = this.triageService.findById(triageupdateDto.getId());
        log.info("**** triage caricato" + triage.toString());
        if(triage == null){
            String errMsg = String.format("Triage con id: %s non esiste", triage.getId());
            log.warning(errMsg);
            throw new Exception(errMsg);
        }
        // In triage we can change doctor but not patient or operator
        if(triageupdateDto.getDoctor_id() != null) {
            if (triage.getDoctor().getId() != triageupdateDto.getDoctor_id()) {
                Doctor doctor = (Doctor) this.userService.getById(triageupdateDto.getDoctor_id());
                triage.setDoctor(doctor);
            }
        }
        //change other param
        if(triageupdateDto.getTriageColor() != null){
            triage.setTriagecolor(triageupdateDto.getTriageColor());
        }
        if(triageupdateDto.getNotes() != null){
            triage.setNotes(triageupdateDto.getNotes());
        }
        this.triageService.save(triage);
        return new ResponseEntity<String>("Modifica Triage eseguito con successo", HttpStatus.CREATED);
    }

    @SneakyThrows
    @DeleteMapping(value = "/delete/id/{id}", produces = "application/json")
    public ResponseEntity<?> deletePatient(@PathVariable("id") Long id){
        log.info("********* Delete Patient with id " + id);
        Triage triage = triageService.findById(id);
        if(triage == null){
            String errMsg = String.format("Triage con id: %s non esiste", id);
            log.warning(errMsg);
            throw new Exception(errMsg);
        }
        this.triageService.delete(triage);

        //Costruzione di un oggetto json
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode responseNode = mapper.createObjectNode();

        responseNode.put("code", HttpStatus.OK.toString());
        responseNode.put("message", "Eliminazione Triage " + id + " eseguita con successo" );
        return new ResponseEntity<>(responseNode, new HttpHeaders(), HttpStatus.OK);
    }
}
