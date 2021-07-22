package br.com.codart.model;

public class Address {

    private String cnpj;
    private String country;
    private String state;
    private String city;
    private String street;
    private String number;

    public Address() {
    }

    public Address(String cnpj, String country, String state, String city,
            String street, String number) {
        this.cnpj = cnpj;
        this.country = country;
        this.state = state;
        this.city = city;
        this.street = street;
        this.number = number;
    }

    public String getCountry() {
        return country;
    }

    public String getState() {
        return state;
    }

    public String getCity() {
        return city;
    }

    public String getStreet() {
        return street;
    }

    public String getNumber() {
        return number;
    }

    public String getCnpj() {
        return cnpj;
    }

}
