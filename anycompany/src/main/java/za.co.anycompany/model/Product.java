package za.co.anycompany.model;

import org.springframework.data.rest.core.annotation.RestResource;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@RestResource(path = "products",rel = "products")
public class Product {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int productId;
  @NotNull
  private String name;

  private Date createdAt = new Date();

  @NotNull
  private int quantity;

  @NotNull
  private double price;

  public Product(){
  }

  public Product(String name, int quantity, double price){
    this.name = name;
    this.quantity = quantity;
    this.price = price;
  }

  public String getName(){
    return name;
  }

  public void setName(String name){
    this.name = name;
  }

  public Date getCreatedAt(){
    return createdAt;
  }

  public void setCreatedAt(Date createdAt){
    this.createdAt = createdAt;
  }

  public int getQuantity(){
    return quantity;
  }

  public void setQuantity(int quantity){
    this.quantity = quantity;
  }

  public double getPrice(){
    return price;
  }

  public void setPrice(double price){
    this.price = price;
  }

  public int getProductId(){
    return productId;
  }

  public void setProductId(int productId){
    this.productId = productId;
  }

  @Override
  public String toString(){
    return "Product{" +
            "productId=" + productId +
            ", name='" + name + '\'' +
            ", createdAt=" + createdAt +
            ", quantity=" + quantity +
            ", price=" + price +
            '}';
  }
}
