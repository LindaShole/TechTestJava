package za.co.anycompany.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.co.anycompany.dto.NewOrderDTO;
import za.co.anycompany.dto.OrderDTO;
import za.co.anycompany.service.OrderService;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("order")
public class OrderController {
    private final OrderService service;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UUID> createOrder(@Valid @RequestBody NewOrderDTO request) {
        return ResponseEntity.ok(service.placeOrder(request));
    }

    @GetMapping(path = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<OrderDTO> getOrder(@PathVariable("id") UUID id) {
        return ResponseEntity.ok(service.getOrder(id));
    }
}
