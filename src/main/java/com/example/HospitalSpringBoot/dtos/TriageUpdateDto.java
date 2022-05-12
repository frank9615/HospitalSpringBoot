package com.example.HospitalSpringBoot.dtos;

import com.example.HospitalSpringBoot.enums.TriageColor;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
public class TriageUpdateDto {
    @NotNull
    private Long id;
    private TriageColor triageColor;
    private Long doctor_id;
    private String notes;
}
