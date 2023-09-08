package za.co.anycompany.dto;

import jakarta.validation.constraints.DecimalMin;

import java.util.UUID;

public record NewOrderDTO(
        @DecimalMin(value = "0", inclusive = false)
        Double amount,

        UUID customerId
) { }
