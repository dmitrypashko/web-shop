package com.gmail.dmitrypashko.dmitry.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "T_Order")
public class Order implements Serializable {

    private static final long serialVersionUID = 56724356867980L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "F_ID")
    private Long id;
    @Enumerated(EnumType.STRING)
    @Column(name = "F_STATUS_ORDER",columnDefinition="enum('NEW','SENT','RECEIVED')")
    private StatusOrder statusOrder;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "order")
    private List<ProductsInOrders> productsInOrders;
    @ManyToOne
    @JoinColumn(name ="F_USER_ID")
    private User user;


    public Order() {
    }

    public StatusOrder getStatusOrder() {
        return statusOrder;
    }

    public void setStatusOrder(StatusOrder statusOrder) {
        this.statusOrder = statusOrder;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<ProductsInOrders> getProductsInOrders() {
        return productsInOrders;
    }

    public void setProductsInOrders(List<ProductsInOrders> productsInOrders) {
        this.productsInOrders = productsInOrders;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
