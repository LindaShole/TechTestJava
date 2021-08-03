package za.co.anycompany;

import org.jetbrains.annotations.NotNull;
import org.junit.Assert;
import org.junit.Test;
import za.co.anycompany.dto.CustomerDto;
import za.co.anycompany.translator.Translator;

/**
 * I could have added more tests its just that the time was not on my side
 */
public class TranlaterTest {
    @Test
    public void toCustomer(){
        CustomerDto dto = getCustomerDto();
        Assert.assertNotNull(Translator.toCustomer(dto));
    }

    @NotNull
    private CustomerDto getCustomerDto() {
        CustomerDto dto = new CustomerDto();
        dto.setCountry("UK");
        return dto;
    }
}
