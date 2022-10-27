package za.co.anycompany.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import za.co.anycompany.dto.CustomerDto;
import za.co.anycompany.dto.OrderDto;
import za.co.anycompany.service.CustomerService;
import za.co.anycompany.service.OrderService;
import za.co.anycompany.validator.AnyCompanyRequestValidator;

@RestController
@RequestMapping(path = "/api/v1")
public class AnyCompanyController {

	@Autowired
	private CustomerService customerService;

	@Autowired
	private OrderService orderService;
	
	@Autowired
	private AnyCompanyRequestValidator anyCompanyRequestValidator;
	
	@InitBinder
    private void initBinder(WebDataBinder binder) {
        binder.setValidator(anyCompanyRequestValidator);
    }

	@PostMapping(path = "/createCustomer")
	public ResponseEntity<CustomerDto> createCustomer(@Valid @RequestBody CustomerDto customer) {
		customer = customerService.saveCustomerDetails(customer);
		return new ResponseEntity<CustomerDto>(customer, HttpStatus.OK);
	}

	@GetMapping(path = "/getCustomer")
	public ResponseEntity<CustomerDto> getCustomerDetails(@RequestParam int customerId) {
		CustomerDto customerDto = customerService.getCustomerDetails(customerId);
		return new ResponseEntity<CustomerDto>(customerDto, HttpStatus.OK);
	}

	@GetMapping(path = "/getAllCustomers")
	public ResponseEntity<List<CustomerDto>> getAllCustomers() {
		return new ResponseEntity<List<CustomerDto>>(customerService.getAllCustomerDetails(), HttpStatus.OK);
	}

	@PostMapping(path = "/createOrder")
	public ResponseEntity<OrderDto> createOrder(@Valid @RequestBody OrderDto order) {
		return new ResponseEntity<OrderDto>(orderService.createOrder(order), HttpStatus.OK);
	}
}
