package za.co.anycompany.controller;

import org.springframework.ui.Model;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import za.co.anycompany.model.Customer;
import za.co.anycompany.model.Order;
import za.co.anycompany.service.CustomerService;
import za.co.anycompany.service.OrderService;

@Controller
public class AnycompanyController {
    
    @Autowired
	CustomerService service;

    @Autowired
	OrderService orderservice;

    //Customer controller
    @GetMapping("/")
    public String home(Model model) {

        List<Customer> list = service.getAllCustomers();
		model.addAttribute("customers", list);

        return "home";
    }

    @RequestMapping(path = {"/customer", "/customer/{customerId}"})
	public String editCustomer(Model model, @PathVariable("customerId") Optional<Integer> id) 
	{
		if (id.isPresent()) { 
			Customer customer = service.getCustomer(id.get());
			model.addAttribute("customer", customer);
		} else {
			model.addAttribute("customer", new Customer());
		}
		
		return "add-edit-customer";
	}

    @RequestMapping(path = "/delete/{customerId}")
	public String deleteCustomerById(Model model, @PathVariable("customerId") Integer id)
	{
		service.deleteCustomer(id);
		return "redirect:/";
	}


    @RequestMapping(path = "/customer", method = RequestMethod.POST)
	public String createOrUpdateCustomer(Customer customer) 
	{
		service.createOrUpdateCustomer(customer);
		return "redirect:/";
	}


    //Order controller
    @RequestMapping(path = {"/orders", "/orders/{customerId}"})
    public String orders(Model model, @PathVariable("customerId") Optional<Integer> id) {

        
        if (id.isPresent()) { 
			List<Order> list = orderservice.getCustomerOrders(id.get());
		    model.addAttribute("orders", list);
		} else {
			List<Order> list = orderservice.getAllOrders();
		    model.addAttribute("orders", list);
		}

        return "orders";
    }

    @RequestMapping(path = {"/order", "/order/{orderId}"})
	public String editOrder(Model model, @PathVariable("orderId") Optional<Integer> id) 
	{
		List<Customer> list = service.getAllCustomers();
		model.addAttribute("customers", list);

		if (id.isPresent()) { 
			Order order = orderservice.getOrder(id.get());
			model.addAttribute("order", order);
		} else {
			model.addAttribute("order", new Order());
		}
		
		return "add-edit-order";
	}

    @RequestMapping(path = "/order", method = RequestMethod.POST)
	public String createOrder(Order order) 
	{
        Boolean results = orderservice.placeOrder(order,order.getCustomerId());

		if(results == true){
            return "redirect:/orders";
        }
        else{
            return "redirect:/add-edit-order";
        }
	}

    @RequestMapping(path = "/delete-order/{orderId}")
	public String deleteOrder(Model model, @PathVariable("orderId") Integer id)
	{
		orderservice.deleteOrder(id);
		return "redirect:/orders";
	}

}
