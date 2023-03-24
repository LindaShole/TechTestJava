package za.co.anycompany;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import za.co.anycompany.datalayer.OrderRepository;
import za.co.anycompany.model.Customer;

//@DataJpaTest
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class OrderModelTests {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private OrderRepository repository;

    @BeforeAll
    static void setupBeforeAll(){

    }
    @BeforeEach
    void setupBefore(){

    }

    @Test
    void checkIfTheTestsAreRunningOnTheOrdersModels(){
        assertEquals(true, true);
    }

    @Test
    void testIfTheOrderIsCreated(){
      //  Customer customer = entityManager.persist(new Customer());
    }
    @AfterEach
    void setupAfter(){

    }

    @AfterAll
    static void setupAfterAll(){

    }

}
