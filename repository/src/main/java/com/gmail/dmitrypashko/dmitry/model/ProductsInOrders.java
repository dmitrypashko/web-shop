package com.gmail.dmitrypashko.dmitry.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "T_PRODUCTS_IN_ORDERS")
public class ProductsInOrders implements Serializable {

    private static final long serialVersionUID = 432789675L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "F_ID")
    private Long id;
    @Column(name = "Quantity")
    private int quantity;
    @ManyToOne
    @JoinColumn(name = "F_PRODUCT_ID")
    private Product product;
    @ManyToOne
    @JoinColumn(name ="F_ORDER_ID")
    private Order order;

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public ProductsInOrders() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
