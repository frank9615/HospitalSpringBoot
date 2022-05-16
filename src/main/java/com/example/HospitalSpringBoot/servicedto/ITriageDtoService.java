package com.example.HospitalSpringBoot.servicedto;
import com.example.HospitalSpringBoot.dtos.TriageDto;

import java.util.List;


public interface ITriageDtoService {
    TriageDto findById(Long id);
    List<TriageDto> getByDoctorId(Long id);
    List<TriageDto> getByPatientId(Long id);
    List<TriageDto> getByOperatorId(Long id);
    List<TriageDto> getAll();
}
