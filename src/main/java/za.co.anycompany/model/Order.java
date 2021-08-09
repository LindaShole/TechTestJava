package za.co.anycompany.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="Orders")
public class Order implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable=false, nullable=false)
    private int id;

    @Column(name = "amount", nullable=false)
    private double amount;

    @Column(name = "VAT", nullable=false)
    private double VAT;

    @ManyToOne
    @JoinColumn(name="customer_id")
    private Customer customer;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getVAT() {
        return VAT;
    }

    public void setVAT(double VAT) {
        this.VAT = VAT;
    }
}
