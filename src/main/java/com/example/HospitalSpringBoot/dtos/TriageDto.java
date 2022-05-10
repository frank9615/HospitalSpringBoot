package com.example.HospitalSpringBoot.dtos;

import com.example.HospitalSpringBoot.enums.TriageColor;
import lombok.Data;

import java.util.Date;
@Data
public class TriageDto {
    private Long id;
    private TriageColor triageColor;
    private Date triageDate;
    private Long patient_id;
    private Long doctor_id;
    private Long operator_id;

}
