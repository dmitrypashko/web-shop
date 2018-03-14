package com.gmail.dmitrypashko.dmitry;

import com.gmail.dmitrypashko.dmitry.model.Order;

import java.util.List;

public interface IOrderRepository extends IGenericDao<Order, Long> {

    void changeStatus(String status, Long id);

    List<Order> getOrdersByUserEmail(String email, int pagination);
}
