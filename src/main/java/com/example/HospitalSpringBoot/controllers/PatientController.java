package com.example.HospitalSpringBoot.controllers;


import com.example.HospitalSpringBoot.dtos.PatientDto;
import com.example.HospitalSpringBoot.entities.Patient;
import com.example.HospitalSpringBoot.servicedto.IPatientDtoService;
import com.example.HospitalSpringBoot.services.IPatientService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.SneakyThrows;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("api/patients")
@Log
public class PatientController {

    @Autowired
    private IPatientService patientService;

    @Autowired
    private IPatientDtoService patientDtoService;

    @GetMapping(produces = "application/json")
    @SneakyThrows
    public ResponseEntity<List<PatientDto>> getPatients(){
        log.info("*** Ottengo la lista dei pazienti ***");
        List<PatientDto> patients = patientDtoService.getAll();
        if(patients.isEmpty()){
            String errMsg = "Non esiste nessun paziente";
            log.warning(errMsg);
            throw new Exception(errMsg);
        }
        return new ResponseEntity<List<PatientDto>>(patients, HttpStatus.OK);
    }

    @GetMapping(value = "/search/id/{id}", produces = "application/json")
    @SneakyThrows
    public ResponseEntity<PatientDto> findById(@PathVariable("id") Long id){
        log.info("****** Ottengo il paziente con l\'id : " + id + " *******");
        PatientDto patient = patientDtoService.findById(id);

        if(patient == null){
            String errMsg = String.format("Il paziente con l\'id: %s non è stato trovato", id);
            log.warning(errMsg);
            throw new Exception(errMsg);
        }
        return new ResponseEntity<PatientDto>(patient, HttpStatus.OK);
    }

    @GetMapping(value = "/search/cf/{cf}", produces = "application/json")
    @SneakyThrows
    public ResponseEntity<PatientDto> findById(@PathVariable("cf") String cf){
        log.info("****** Ottengo il paziente con l\'id : " + cf + " *******");
        PatientDto patient = patientDtoService.findByCF(cf);

        if(patient == null){
            String errMsg = String.format("Il paziente con l\'id: %s non è stato trovato", cf);
            log.warning(errMsg);
            throw new Exception(errMsg);
        }
        return new ResponseEntity<PatientDto>(patient, HttpStatus.OK);
    }

    @SneakyThrows
    @PostMapping(value = "/new")
    public ResponseEntity<String> createPatient(@Valid @RequestBody Patient patient , BindingResult bindingResult){
        log.info("******* New Patient " + patient.getCf());
        if(bindingResult.hasErrors()){
            String errMsg = bindingResult.getFieldError().toString();
            log.warning(errMsg);
            throw new Exception(errMsg);
        }
        patient.setRegistrationdate(new Date());
        this.patientService.save(patient);
        return new ResponseEntity<String>("Insetimento Paziente eseguito con successo", HttpStatus.CREATED);
    }

    @SneakyThrows
    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public ResponseEntity<String> updatePatient(@Valid @RequestBody Patient patient, BindingResult bindingResult){
        log.info("******* Update Patient " +patient.getCf());
        if(bindingResult.hasErrors()){
            String errMsg = bindingResult.getFieldError().toString();
            log.warning(errMsg);
            throw new Exception(errMsg);
        }
        PatientDto patientDto = this.patientDtoService.findById(patient.getId());
        if(patientDto == null){
            String errMsg = String.format("Paziente con id: %s non esiste", patient.getId());
            log.warning(errMsg);
            throw new Exception(errMsg);
        }
        this.patientService.save(patient);
        return new ResponseEntity<String>("Modifica Paziente eseguito con successo", HttpStatus.CREATED);
    }

    @SneakyThrows
    @DeleteMapping(value = "/delete/id/{id}", produces = "application/json")
    public ResponseEntity<?> deletePatient(@PathVariable("id") Long id){
        log.info("********* Delete Patient with id " + id);
        Patient patient = patientService.findById(id);
        if(patient == null){
            String errMsg = String.format("Paziente con id: %s non esiste", patient.getId());
            log.warning(errMsg);
            throw new Exception(errMsg);
        }
        this.patientService.delete(patient);

        //Costruzione di un oggetto json
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode responseNode = mapper.createObjectNode();

        responseNode.put("code", HttpStatus.OK.toString());
        responseNode.put("message", "Eliminazione Paziente " + id + " eseguita con successo" );
        return new ResponseEntity<>(responseNode, new HttpHeaders(), HttpStatus.OK);
    }

    //get list of patients from doctor_id
    @SneakyThrows
    @GetMapping(value = "/assigned/doctor/id/{id}", produces = "application/json")
    public ResponseEntity<List<PatientDto>> getPatientsAssignedToDoctorId(@PathVariable("id") Long id){
        log.info("*** Ottengo la lista dei pazienti assegnati al doctor id "+ id+ "***");
        List<PatientDto> patients = patientDtoService.getPatientAssignedToDoctor_Id(id);
        if(patients.isEmpty()){
            String errMsg = "Non esiste nessun paziente assegnato al dottore con l\'id indicato";
            log.warning(errMsg);
            throw new Exception(errMsg);
        }
        patients.forEach(System.out::println);
        return new ResponseEntity<List<PatientDto>>(patients, HttpStatus.OK);
    }




}
