package za.co.anycompany.dto;

import java.util.UUID;

public record OrderDTO(UUID id, Double amount, Double vat) {
}
