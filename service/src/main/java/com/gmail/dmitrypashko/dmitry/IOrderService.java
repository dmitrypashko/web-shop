package com.gmail.dmitrypashko.dmitry;

import com.gmail.dmitrypashko.dmitry.model.User;
import com.gmail.dmitrypashko.dmitry.modelDTO.OrderDTO;

import java.util.List;

public interface IOrderService {

    List<OrderDTO> showOrdersByUserEmail(String email, int pagination);

    void saveOrder(String[] idProduct, String[] quantity, User user);

    List<OrderDTO> showOAllOrders(int pagination);

    List<Integer> getPaginationList();

    void changeStatus(String status, Long id);

}
