package za.co.anycompany.domain;

import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.*;

@Entity
@Table(name = "ORDERS")
@Access(AccessType.FIELD)
@SequenceGenerator(name = "seq_order", sequenceName = "seq_order", allocationSize = 1, initialValue = 1)
public class Order extends AbstractPersistable<Long> {

    private double amount;

    private double vat;
    @ManyToOne
    private Customer customer;

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

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
