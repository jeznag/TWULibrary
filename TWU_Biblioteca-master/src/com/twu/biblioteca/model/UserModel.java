package com.twu.biblioteca.model;

/**
 * Created by jeremynagel on 6/03/15.
 */
public class UserModel {


    public UserModel(String name, String email, String phone,  String cardNumber, String password) {
        this.phone = phone;
        this.name = name;
        this.email = email;
        this.cardNumber = cardNumber;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean tryLogin(String cardNumber, String password){
        return (cardNumber.equals(this.cardNumber) && password.equals(this.password)) ? true : false;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getSummary(){
        return "Name: " + getName() + "\nEmail: " + getEmail() +"\nPhone: " + getPhone() + "\n";
    }

    public String getSummaryForWhoDunnit(){
        return getName() + " has that item";
    }


    private String phone;
    private String name;
    private String email;
    private String cardNumber;
    private String password;

}
