package java.za.co.anycompany;

import com.sun.org.glassfish.gmbal.NameValue;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.context.properties.bind.Name;

public class CalculatorTest {




  @Test
   public void test_add_10_and_10(){
     Calculator test = new Calculator();
     double sum = test.add(10,10);


   }
}
