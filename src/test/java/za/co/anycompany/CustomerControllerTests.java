package za.co.anycompany;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class CustomerControllerTests {

    @BeforeAll
    static void setupBeforeAll(){

    }
    @BeforeEach
    void setupBefore(){

    }

    @Test
    void checkIfTheTestsAreRunningOnTheCustomersControllers(){
        assertEquals(true, true);
    }

    @AfterEach
    void setupAfter(){

    }

    @AfterAll
    static void setupAfterAll(){

    }
}