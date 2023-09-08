package za.co.anycompany.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.sql.Date;

public record NewCustomerRequestDTO(
        @NotBlank
        String name,

        @NotBlank
        String country,

        @NotNull
        Date dateOfBirth
) { }
