package za.co.anycompany.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@Import({VatCalculatorImpl.class})
@ExtendWith(SpringExtension.class)
class VatCalculatorTests {
    @Autowired
    private VatCalculator calculator;

    @Test
    void calculate_nullCountry_throwsException() {
        assertThrows(IllegalArgumentException.class, () -> calculator.calculate(null));
    }

    @Test
    void calculate_ukCountry_returnsCorrectValue() {
        var result = calculator.calculate("UK");
        assertEquals(0.2D, result);
    }

    @Test
    void calculate_otherCountry_returnsCorrectValue() {
        var result = calculator.calculate("ZA");
        assertEquals(0D, result);
    }
}
