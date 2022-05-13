package com.example.HospitalSpringBoot.dtos;

import com.example.HospitalSpringBoot.enums.TriageColor;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Date;
@Data
public class TriageDto {
    private Long id;
    @NotNull
    private TriageColor triageColor;

    private Date triageDate;
    @NotNull
    private Long patient_id;
    @NotNull
    private Long doctor_id;
    @NotNull
    private Long operator_id;
    @NotNull
    private String notes;

}
