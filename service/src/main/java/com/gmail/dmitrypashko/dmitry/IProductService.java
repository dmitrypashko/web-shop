package com.gmail.dmitrypashko.dmitry;

import com.gmail.dmitrypashko.dmitry.model.Product;

import java.util.List;

public interface IProductService {

    void addProduct(Product product);

    List<Product> getAllProduct(int pagination);

    Product getProduct(Long idProduct);

    void deleteProduct(Long idProduct);

    List<Integer> getPaginationList();
}
