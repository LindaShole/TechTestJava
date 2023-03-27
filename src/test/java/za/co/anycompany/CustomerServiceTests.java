package za.co.anycompany;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import za.co.anycompany.datalayer.CustomerRepository;
import za.co.anycompany.model.Customer;
import za.co.anycompany.service.CustomerService;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class CustomerServiceTests {

    @MockBean
    private CustomerRepository repository;

    @InjectMocks
    private CustomerService service;
    @BeforeAll
    static void setupBeforeAll(){

    }
    @BeforeEach
    void setupBefore(){

    }

    @Test
    void checkIfTheTestsAreRunningOnTheCustomerService(){
        assertEquals(true, true);
    }

    @Test
    void getsTheCorrectCustomerCorrespondingToACustomerId(){
        final Customer customer = new Customer();
        customer.setCountry("SA");
        customer.setId(1);
        customer.setCustomerid(1);
        customer.setName("Xolisani");

        CustomerService customerServiceMock = mock(CustomerService.class);
       // customerServiceMock.getCustomerByIdTest(1) .getAllCustomers()
        when(customerServiceMock.getCustomerById(2)).thenReturn(customer);
      //  given(repository.existsById(1)).willReturn(Optional.empty());


    //    Customer testCustomer = new Customer(customerServiceMock);

       /* CustomerService mock = org.mockito.Mockito.mock(CustomerService.class);

       // Mockito.when(repository.load(1)).thenReturn(testCustomer);
        Customer cst = service.getCustomerById(1);
        assertTrue(repository.existsById(1));
        assertEquals(customer.getName(), testCustomer.getName());*/
      //  assertEquals(customer.getName(), testCustomer.getName());
    }


    @Test
    void getsTheCorrectCustomerByIDfromTheDB(){
       // Optional<Customer> customer = repository.findById(2);

     /*   Mockito.when(repository.findById(2)).thenReturn(null);

        Customer cst = service.getCustomerById(2);

        assertEquals(cst, cst);*/
    }

    @AfterEach
    void setupAfter(){

    }

    @AfterAll
    static void setupAfterAll(){

    }
}
