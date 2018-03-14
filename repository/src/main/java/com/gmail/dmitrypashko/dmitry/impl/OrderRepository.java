package com.gmail.dmitrypashko.dmitry.impl;

import com.gmail.dmitrypashko.dmitry.model.StatusOrder;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import com.gmail.dmitrypashko.dmitry.IOrderRepository;
import com.gmail.dmitrypashko.dmitry.model.Order;

import java.util.List;

@Repository
public class OrderRepository extends GenericDao<Order, Long> implements IOrderRepository {


    @Override
    public void changeStatus(String status, Long id) {
        Query query = getSession().createQuery("update Order set statusOrder=:status where id=:id");
        query.setParameter("id", id);
        query.setParameter("status", StatusOrder.valueOf(status));
        query.executeUpdate();

    }

    @Override
    public List<Order> getOrdersByUserEmail(String email, int pagination) {
        Query query = getSession().createQuery("FROM Order where user.email=:email");
        query.setFirstResult(5 * pagination);
        query.setMaxResults(5);
        query.setParameter("email", email);
        return query.list();
    }

}
