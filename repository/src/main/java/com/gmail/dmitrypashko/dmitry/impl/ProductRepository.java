package com.gmail.dmitrypashko.dmitry.impl;

import org.springframework.stereotype.Repository;
import com.gmail.dmitrypashko.dmitry.IProductRepository;
import com.gmail.dmitrypashko.dmitry.model.Product;


@Repository
public class ProductRepository extends GenericDao<Product, Long> implements IProductRepository {


}
