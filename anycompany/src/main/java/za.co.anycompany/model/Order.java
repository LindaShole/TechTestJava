package za.co.anycompany.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.rest.core.annotation.RestResource;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Table(name = "orders")
@Entity
@RestResource(path = "orders",rel = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long orderId;

    private double Subtotal ;

    private Date placedAt = new Date();
    @ManyToOne
    @JoinColumn(name = "customer_id")
    @JsonIgnore // resolve the infinite recursion
    private Customer customer;

    @OneToMany(mappedBy = "order",cascade = CascadeType.ALL)
    private List<Product> products;

    public Order(){
    }

    public Order(List<Product> products,Customer customer){
       this.products = products;
        this.customer = customer;
        this.setSubtotal();
    }

    public double getSubtotal(){
        return Subtotal;
    }

    public void setSubtotal(){

        for(Product product : products){
            Subtotal += product.getQuantity() * product.getPrice();
        }
    }

    public List<Product> getProducts(){
        return products;
    }



    public void setProducts(List<Product> products){
        this.products = products;
        this.Subtotal = 0;
        this.setSubtotal();
    }

    public Date getPlacedAt(){
        return placedAt;
    }

    public void setPlacedAt(Date placedAt){
        this.placedAt = placedAt;
    }



    public Long getOrderId(){
        return orderId;
    }

    public void setOrderId(Long orderId){
        this.orderId = orderId;
    }

    public Customer getCustomer(){
        return customer;
    }

    public void setCustomer(Customer customer){
        this.customer = customer;
    }



    @Override
    public String toString(){
        return "Order{" +
                "orderId=" + orderId +
                ", Subtotal=" + Subtotal +
                ", customer=" + customer +
                ", products=" + products +
                '}';
    }
}
