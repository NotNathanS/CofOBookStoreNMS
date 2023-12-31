/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cofobookstorenms;

import java.io.Serializable;
/**
 *
 * @author 221384
 */
public class Customer {
    
    private Integer customerID;
    private String firstName;
    private String lastName;
    private String address;
    private String city;
    private String state;
    private String zipCode;
    private String phoneNbr;
    private String email;
    private String password;
    private String creditCardCode;
    private String creditCardNbr;

    public Customer() {
        this.customerID = -1;
    }

    public Customer(Integer customerID, String firstName, String lastName, String address, String city, String state, String zipCode, String phoneNbr, String email, String password, String creditCardCode, String creditCardNbr) {
        this.customerID = customerID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
        this.phoneNbr = phoneNbr;
        this.email = email;
        this.password = password;
        this.creditCardCode = creditCardCode;
        this.creditCardNbr = creditCardNbr;
    }

    public Integer getCustomerID() {
        return customerID;
    }

    public void setCustomerID(Integer customerID) {
        this.customerID = customerID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getPhoneNbr() {
        return phoneNbr;
    }

    public void setPhoneNbr(String phoneNbr) {
        this.phoneNbr = phoneNbr;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCreditCardCode() {
        return creditCardCode;
    }

    public void setCreditCardCode(String creditCardCode) {
        this.creditCardCode = creditCardCode;
    }

    public String getCreditCardNbr() {
        return creditCardNbr;
    }

    public void setCreditCardNbr(String creditCardNbr) {
        this.creditCardNbr = creditCardNbr;
    }


    @Override
    public String toString() {
        return "orubookstoreapp.Customer[ customerID=" + customerID + " ]";
    }
    
}

