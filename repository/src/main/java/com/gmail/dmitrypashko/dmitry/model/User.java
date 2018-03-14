package com.gmail.dmitrypashko.dmitry.model;


import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "T_USER")
public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "F_ID",nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "F_NAME",nullable = false)
    private String name;
    @Column(name = "F_SURNAME",nullable = false)
    private String surname;
    @Column(name = "F_EMAIL",nullable = false)
    private String email;
    @Column (name = "F_PHONE_NUMBER",nullable = false)
    private String phoneNumber;
    @Column(name = "F_PASSWORD",nullable = false)
    private String password;

    @Transient
    private String confirmPassword;

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    private List<Order> orders = new ArrayList<>();

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    private List<Comment> comments = new ArrayList<Comment>();

    @OneToOne(mappedBy = "user",cascade = CascadeType.ALL)
    private Address address;

    @Enumerated(EnumType.STRING)
    @Column(name = "F_ROLE",columnDefinition="enum('ROLE_USER','ROLE_ADMIN','ROLE_SUPER_ADMIN')")
    private Role role;

    @Enumerated(EnumType.STRING)
    @Column(name = "F_STATUS",columnDefinition="enum('STATUS_UNBLOCKED','STATUS_BLOCKED')")
    private StatusUser statusUser;


    public User() {
    }



    public StatusUser getStatusUser() {
        return statusUser;
    }

    public void setStatusUser(StatusUser statusUser) {
        this.statusUser = statusUser;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

}
