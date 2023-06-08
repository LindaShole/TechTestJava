package za.co.anycompany.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class Address {


  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long Id;

  @NotNull
  private String street;

  @NotNull
  private String city;

  @NotNull
  private String province;

  @OneToOne(mappedBy = "address")
  @JsonIgnore
  Customer customer;

  @NotNull
  private String zip;

  public Address(){}

  public Address(String street, String city, String province, String zip){
    this.street = street;
    this.city = city;
    this.province = province;
    this.zip = zip;
  }

  public Long getId(){
    return Id;
  }

  public void setId(Long id){
    Id = id;
  }

  public String getStreet(){
    return street;
  }

  public void setStreet(String street){
    this.street = street;
  }

  public String getCity(){
    return city;
  }

  public void setCity(String city){
    this.city = city;
  }

  public String getProvince(){
    return province;
  }

  public void setProvince(String province){
    this.province = province;
  }

  public String getZip(){
    return zip;
  }

  public void setZip(String zip){
    this.zip = zip;
  }

  public Customer getCustomer(){
    return customer;
  }

  public void setCustomer(Customer customer){
    this.customer = customer;
  }

  @Override
  public String toString(){
    return "Address{" +
            "Id=" + Id +
            ", street='" + street + '\'' +
            ", city='" + city + '\'' +
            ", province='" + province + '\'' +
            ", customer=" + customer +
            ", zip='" + zip + '\'' +
            '}';
  }
}
