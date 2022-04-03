package com.service.order.orderhistory.service;

import com.service.order.orderhistory.domain.OrderHistory;
import com.service.order.orderhistory.exception.OrderNotFoundException;
import com.service.order.orderhistory.repository.OrderHistoryDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderHistoryService implements OrderHistoryServiceAPI {

    private final OrderHistoryDAO orderHistoryDAO;

    @Autowired
    public OrderHistoryService(OrderHistoryDAO orderHistoryDAO){
        this.orderHistoryDAO = orderHistoryDAO;
    }

    @Override
    public List<OrderHistory> getAllOrders() {
        return orderHistoryDAO.findAll();
    }

    @Override
    public OrderHistory getOrderById(Long orderId) {
        return orderHistoryDAO.findById(orderId).orElse(null);
    }

    @Override
    public OrderHistory updateOrderDeliveryStatusById(Long orderId, String deliveryStatus) throws OrderNotFoundException {
        OrderHistory order = orderHistoryDAO.findById(orderId).orElse(null);
        if(order == null)
            throw new OrderNotFoundException(orderId);
        order.setDeliveryStatus(deliveryStatus);
        return orderHistoryDAO.save(order);
    }

    @Override
    public OrderHistory addOrderHistory(OrderHistory order) {
        return orderHistoryDAO.save(order);
    }
}
