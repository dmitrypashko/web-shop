package com.gmail.dmitrypashko.dmitry.impl;

import com.gmail.dmitrypashko.dmitry.IProductInOrderRepository;
import org.springframework.stereotype.Repository;
import com.gmail.dmitrypashko.dmitry.model.ProductsInOrders;

@Repository
public class ProductInOrderRepository extends GenericDao<ProductsInOrders, Long> implements IProductInOrderRepository {
}
