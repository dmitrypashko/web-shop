package com.gmail.dmitrypashko.dmitry.model;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;

@Entity
@Table(name = "T_PRODUCT")
public class Product implements Serializable {
    private static final long serialVersionUID = 986543L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "F_ID")
    private Long id;
    @Column(name = "F_PRICE")
    private BigInteger price;
    @Column(name = "F_NAME_PRODUCT")
    private String nameProduct;
    @Column(name = "F_DESCRIPTION")
    private String description;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "product")
    private List<ProductsInOrders> productsInOrders;

    public Product() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigInteger getPrice() {
        return price;
    }

    public void setPrice(BigInteger price) {
        this.price = price;
    }

    public String getNameProduct() {
        return nameProduct;
    }

    public void setNameProduct(String nameProduct) {
        this.nameProduct = nameProduct;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<ProductsInOrders> getProductsInOrders() {
        return productsInOrders;
    }

    public void setProductsInOrders(List<ProductsInOrders> productsInOrders) {
        this.productsInOrders = productsInOrders;
    }
}
