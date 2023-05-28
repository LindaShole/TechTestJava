package za.com.anyCompany.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import za.com.anyCompany.Repo.CustomerRepo;
import za.com.anyCompany.model.CustomerModel;

import java.util.List;


@RequiredArgsConstructor
@Service
public class CustomerService {
    private final  CustomerRepo customerRepository;

    public List<CustomerModel> findAllCustomers(){
        return customerRepository.findAll();
        // depending on the amount of customers we have in the database, we can implement pagination
        // otherwise we will run out of memory..
    }
    public void checkIfCustomerExists(CustomerModel customerModel){

        CustomerModel customer =  customerRepository.findById(customerModel.getId());
        if (customer==null)
            customerRepository.save(customerModel);
    }
}
