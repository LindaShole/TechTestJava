package za.com.anyCompany.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import za.com.anyCompany.DTO.RequestDto;
import za.com.anyCompany.DTO.ResponseDto;
import za.com.anyCompany.Service.CustomerService;
import za.com.anyCompany.Service.OrderService;
import za.com.anyCompany.model.CustomerModel;
import za.com.anyCompany.model.OrderModel;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/customer")
@Slf4j
public class CustomerController {
    private final OrderService orderService;
    private final CustomerService customerService;
    @RequestMapping("/ping")
    ResponseEntity<Integer> healthCheck() {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping("/placeNewOrder")
    ResponseEntity<?> createOrder(@RequestBody RequestDto requestDto) {
        if (requestDto != null) {
            ResponseDto responseDto = orderService.placeOrder(requestDto);
            if (responseDto.isStatus())
                return new ResponseEntity<>(responseDto.getMessage(), HttpStatus.OK);
            else return new ResponseEntity<>(responseDto.getMessage(), HttpStatus.BAD_REQUEST);
        }
        else return new ResponseEntity<>("Internal Server error", HttpStatus.valueOf(500));
    }


    @RequestMapping("/findAllCustomers")
    ResponseEntity<?> findCustomers() {
        List<CustomerModel> customers = customerService.findAllCustomers();
        if (customers.isEmpty()) {
            return new ResponseEntity<>("Internal Server Error", HttpStatus.valueOf(500));
        } else return new ResponseEntity<>(customers, HttpStatus.OK);
    }
}
