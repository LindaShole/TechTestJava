package za.co.anycompany.dto;

import java.sql.Date;
import java.util.List;
import java.util.UUID;

public record CustomerDTO(UUID id, String name, String country, Date dateOfBirth, List<OrderDTO> orders) {
}
