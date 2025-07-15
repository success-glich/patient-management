package com.pm.patientservice.dto;

import com.pm.patientservice.dto.validators.CreatePatientValidationGroup;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PatientRequestDTO {

    @NotBlank(message = "Name cannot be empty")
    @Size(max=100, message = "Name cannot be more than 100 characters")
    private String name;

    @NotBlank(message = "Email cannot be empty")
    @Size(max=100, message = "Email cannot be more than 100 characters")
    @Email
    private String email;

    @NotBlank(message = "Address cannot be empty")
    @Size(max=100, message = "Address cannot be more than 100 characters")
    private String address;

    @NotBlank(message = "Date of birth cannot be empty")
    @Size(max=100, message = "Date of birth cannot be more than 100 characters")
    private String dateOfBirth;

    @NotNull(groups = CreatePatientValidationGroup.class, message = "Registered date is required.")
    @Size(max=100, message = "Registered date cannot be more than 100 characters")
    private String registeredDate;


}
