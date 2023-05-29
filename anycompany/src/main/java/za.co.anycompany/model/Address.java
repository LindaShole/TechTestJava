package za.co.anycompany.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class Address {


  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int Id;

  @NotNull
  private String street;

  @NotNull
  private String city;

  @NotNull
  private String province;

  @NotNull
  private String zip;

  public Address(){}

  public Address(String street, String city, String province, String zip){
    this.street = street;
    this.city = city;
    this.province = province;
    this.zip = zip;
  }

  public int getId(){
    return Id;
  }

  public void setId(int id){
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

  @Override
  public String toString(){
    return "Address{ street='" + street + '\'' +
            ", city='" + city + '\'' +
            ", province='" + province + '\'' +
            ", zip='" + zip + '\'' +
            '}';
  }
}
