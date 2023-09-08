package za.co.anycompany.dto;

import java.util.UUID;

public record NewOrderDTO(Double amount, UUID customerId) {
}
