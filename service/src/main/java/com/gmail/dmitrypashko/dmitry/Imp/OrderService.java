package com.gmail.dmitrypashko.dmitry.Imp;

import com.gmail.dmitrypashko.dmitry.IOrderRepository;
import com.gmail.dmitrypashko.dmitry.IOrderService;
import com.gmail.dmitrypashko.dmitry.IProductInOrderRepository;
import com.gmail.dmitrypashko.dmitry.IProductRepository;
import com.gmail.dmitrypashko.dmitry.model.*;
import com.gmail.dmitrypashko.dmitry.modelDTO.OrderDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.gmail.dmitrypashko.dmitry.modelDTO.ProductInOrderDTO;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService implements IOrderService {
    @Autowired
    private IOrderRepository orderDao;
    @Autowired
    private IProductRepository productDao;
    @Autowired
    private IProductInOrderRepository productInOrderDao;


    @Override
    @Transactional
    public List<OrderDTO> showOrdersByUserEmail(String email, int pagination) {
        List<Order> orders = orderDao.getOrdersByUserEmail(email, pagination);
        return conversionOrderInOrderDTO(orders);
    }

    @Override
    @Transactional
    public List<Integer> getPaginationList() {
        return orderDao.getPaginationList();
    }

    @Override
    @Transactional
    public void changeStatus(String status, Long id) {
        orderDao.changeStatus(status, id);
    }

    @Override
    @Transactional
    public List<OrderDTO> showOAllOrders(int pagination) {
        List<Order> orders = orderDao.getAll(pagination);
        return conversionOrderInOrderDTO(orders);
    }

    private List<OrderDTO> conversionOrderInOrderDTO(List<Order> orders) {
        List<OrderDTO> ordersDTO = new ArrayList<>();
        for (Order order : orders) {
            List<ProductsInOrders> productsInOrders = order.getProductsInOrders();
            List<ProductInOrderDTO> productInOrderDTO = new ArrayList<>();
            OrderDTO orderDTO = new OrderDTO();
            for (ProductsInOrders inOrders : productsInOrders) {
                Product product = inOrders.getProduct();
                ProductInOrderDTO productInOrderDTO1 = new ProductInOrderDTO();
                productInOrderDTO1.setPrice(product.getPrice());
                productInOrderDTO1.setName(product.getNameProduct());
                productInOrderDTO1.setDescription(product.getDescription());
                productInOrderDTO1.setQuantity(inOrders.getQuantity());
                productInOrderDTO.add(productInOrderDTO1);
            }
            orderDTO.setProductInOrderDTO(productInOrderDTO);
            orderDTO.setId(order.getId());
            orderDTO.setStatus(order.getStatusOrder().name());
            ordersDTO.add(orderDTO);
        }
        return ordersDTO;
    }


    @Override
    @Transactional
    public void saveOrder(String[] idProduct, String[] quantity, User user) {
        Order order = new Order();
        for (int i = 0; i < idProduct.length; i++) {
            Product product = productDao.findById(Long.parseLong(idProduct[i]));
            if (product != null){
                order.setStatusOrder(StatusOrder.NEW);
                order.setUser(user);
                ProductsInOrders productsInOrders = new ProductsInOrders();
                productsInOrders.setOrder(order);
                productsInOrders.setProduct(product);
                productsInOrders.setQuantity(Integer.parseInt(quantity[i]));
                productInOrderDao.saveOrUpData(productsInOrders);
            }
        }
        if (order.getStatusOrder() != null){
            orderDao.saveOrUpData(order);
        }

    }
}
