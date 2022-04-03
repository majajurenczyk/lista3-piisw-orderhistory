package com.service.order.orderhistory.api;

import com.service.order.orderhistory.domain.OrderHistory;
import com.service.order.orderhistory.exception.OrderNotFoundException;
import com.service.order.orderhistory.service.OrderHistoryServiceAPI;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(path = "/orders-history")
@RestController
@Slf4j
public class OrderHistoryController {

    private final OrderHistoryServiceAPI orderHistoryServiceAPI;

    @Autowired
    public OrderHistoryController(OrderHistoryServiceAPI orderHistoryServiceAPI){
        this.orderHistoryServiceAPI = orderHistoryServiceAPI;
    }

    @GetMapping
    public ResponseEntity<List<OrderHistory>> getAllOrders() {
        return new ResponseEntity<>(orderHistoryServiceAPI.getAllOrders(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderHistory> getOrderById(@PathVariable("id") Long orderId) {
        return new ResponseEntity<>(orderHistoryServiceAPI.getOrderById(orderId), HttpStatus.OK);
    }

    @PostMapping("/{id}/delivery")
    @Tag(name = "order-history-api")
    public ResponseEntity<OrderHistory> updateOrderDelivery(@PathVariable("id") Long orderId,
                                                            @RequestParam String deliveryStatus) throws OrderNotFoundException {
        log.info("Requested to change delivery status " + deliveryStatus + "for order with id " + orderId);
        return new ResponseEntity<>(orderHistoryServiceAPI.updateOrderDeliveryStatusById(orderId,
                deliveryStatus), HttpStatus.OK);
    }

    @PostMapping
    @Tag(name = "order-history-api")
    public ResponseEntity<OrderHistory> addNewOrder(@RequestBody @NonNull OrderHistory order){
        log.info("Requested to add new order with id " + order.getOrderId());
        return new ResponseEntity<>(orderHistoryServiceAPI.addOrderHistory(order), HttpStatus.CREATED);
    }
}
