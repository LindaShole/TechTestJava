package za.co.anycompany.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

@Component
public class VatCalculatorImpl implements VatCalculator {
    @Override
    public double calculate(String country) {
        if (StringUtils.isBlank(country))
            throw new IllegalArgumentException("Country is blank.");
        return country.equals("UK") ? 0.2D : 0D;
    }
}
