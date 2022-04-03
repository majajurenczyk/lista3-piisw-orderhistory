package com.service.order.orderhistory;

import com.service.order.orderhistory.domain.OrderHistory;
import com.service.order.orderhistory.repository.OrderHistoryDAO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

import static com.service.order.orderhistory.OrderHistoryTestDataProvider.*;

@SpringBootTest
public class OrderHistoryRepositoryTests {

    @Autowired
    OrderHistoryDAO orderHistoryDAO;

    @Test
    public void shouldSaveOrderHistory(){
        OrderHistory orderHistory = orderHistoryDAO.save(SECOND_ORDER_HISTORY);
        Assertions.assertEquals(2L, orderHistory.getOrderId());
        Assertions.assertEquals("Maja", orderHistory.getCustomerName());
        Assertions.assertEquals("Darek", orderHistory.getCourierName());
        Assertions.assertEquals("PICKED_UP", orderHistory.getDeliveryStatus());
        Assertions.assertEquals("suszarka, spodnie", orderHistory.getProductNames());
        Assertions.assertEquals(BigDecimal.valueOf(14.54), orderHistory.getTotalPrice());
    }

    @Test
    public void shouldFindOrderById(){

        orderHistoryDAO.save(SECOND_ORDER_HISTORY);
        OrderHistory orderHistory = orderHistoryDAO.findById(2L).orElse(null);

        Assertions.assertNotNull(orderHistory);
        Assertions.assertEquals(2L, orderHistory.getOrderId());
        Assertions.assertEquals("Maja", orderHistory.getCustomerName());
        Assertions.assertEquals("Darek", orderHistory.getCourierName());
        Assertions.assertEquals("PICKED_UP", orderHistory.getDeliveryStatus());
        Assertions.assertEquals("suszarka, spodnie", orderHistory.getProductNames());
        Assertions.assertEquals(BigDecimal.valueOf(14.54), orderHistory.getTotalPrice());
    }
}
