package za.co.anycompany.dto;

import jdk.nashorn.internal.objects.annotations.Property;
import org.springframework.data.jpa.domain.AbstractPersistable;
import org.springframework.validation.annotation.Validated;

public class OrderDto {

    private Long id;

    private double amount;

    private double vat;

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getVat() {
        return vat;
    }

    public void setVat(double vat) {
        this.vat = vat;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
