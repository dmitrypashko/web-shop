package com.gmail.dmitrypashko.dmitry.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "Address")
@Table(name = "T_ADDRESS")
public class Address implements Serializable {

    private static final long serialVersionUID = 4L;

    @Id
    @Column(name = "F_USER_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    @Column(name = "F_COUNTRY",nullable = false)
    private String country;
    @Column(name = "F_CITY",nullable = false)
    private String city;
    @Column(name = "F_STREET",nullable = false)
    private String street;
    @Column(name = "F_BUILDING",nullable = false)
    private String building;
    @Column(name = "F_APARTMENT_NUMBER",nullable = false)
    private String apartmentNumber;

    @OneToOne
    @PrimaryKeyJoinColumn(name = "F_USER_ID")
    private User user;

    public Address() {
    }

    public String getApartmentNumber() {
        return apartmentNumber;
    }

    public void setApartmentNumber(String apartmentNumber) {
        this.apartmentNumber = apartmentNumber;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    @Override
    public String toString() {
        return "Address{" +
                "id=" +
                ", city='" + city + '\'' +
                ", street='" + street + '\'' +
                ", building=" + building +
                '}';
    }
}
