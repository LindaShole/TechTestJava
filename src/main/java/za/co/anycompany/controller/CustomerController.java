package za.co.anycompany.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.co.anycompany.dto.CustomerDTO;
import za.co.anycompany.dto.NewCustomerRequestDTO;
import za.co.anycompany.service.CustomerService;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("customer")
public class CustomerController {
    private final CustomerService service;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UUID> createCustomer(@Valid @RequestBody NewCustomerRequestDTO request) {
        return ResponseEntity.ok(service.createCustomer(request));
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<CustomerDTO>> getAllCustomers() {
        return ResponseEntity.ok(service.getAllCustomers());
    }

    @GetMapping(path = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CustomerDTO> getCustomer(@PathVariable("id") UUID id) {
        return ResponseEntity.ok(service.getCustomer(id));
    }
}
