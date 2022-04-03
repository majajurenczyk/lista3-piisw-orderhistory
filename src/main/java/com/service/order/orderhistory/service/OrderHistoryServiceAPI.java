package com.service.order.orderhistory.service;

import com.service.order.orderhistory.domain.OrderHistory;
import com.service.order.orderhistory.exception.OrderNotFoundException;

import java.util.List;

public interface OrderHistoryServiceAPI {
    List<OrderHistory> getAllOrders();
    OrderHistory getOrderById(Long orderId);
    OrderHistory updateOrderDeliveryStatusById(Long orderId, String deliveryStatus) throws OrderNotFoundException;
    OrderHistory addOrderHistory(OrderHistory order);
}
