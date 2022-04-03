package com.service.order.orderhistory;

import com.service.order.orderhistory.domain.OrderHistory;
import com.service.order.orderhistory.exception.OrderNotFoundException;
import com.service.order.orderhistory.repository.OrderHistoryDAO;
import com.service.order.orderhistory.service.OrderHistoryService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.service.order.orderhistory.OrderHistoryTestDataProvider.*;
import static org.mockito.Mockito.when;

@SpringBootTest
public class OrderHistoryServiceTests {

    @Autowired
    @InjectMocks
    OrderHistoryService orderHistoryService;

    @MockBean
    OrderHistoryDAO orderHistoryDAO;


    @Test
    public void shouldGetAllOrders() {
        //given
        List<OrderHistory> allOrders = new ArrayList<>();
        allOrders.add(FIRST_ORDER_HISTORY);
        allOrders.add(SECOND_ORDER_HISTORY);

        //when
        when(orderHistoryDAO.findAll()).thenReturn(allOrders);

        //then
        Assertions.assertEquals(2, orderHistoryService.getAllOrders().size());
        Assertions.assertEquals(FIRST_ORDER_HISTORY, orderHistoryService.getAllOrders().get(0));
        Assertions.assertEquals(SECOND_ORDER_HISTORY, orderHistoryService.getAllOrders().get(1));
    }

    @Test
    public void shouldGetOrderWithGivenId() {
        //given //when
        when(orderHistoryDAO.findById(1L)).thenReturn(Optional.of(FIRST_ORDER_HISTORY));

        //then
        Assertions.assertEquals(FIRST_ORDER_HISTORY, orderHistoryService.getOrderById(1L));
    }

    @Test
    public void shouldAddNewOrder() {
        //given //when
        when(orderHistoryDAO.save(NEW_ORDER_HISTORY)).thenReturn(FIRST_ORDER_HISTORY);

        //then
        Assertions.assertEquals(FIRST_ORDER_HISTORY, orderHistoryService.addOrderHistory(NEW_ORDER_HISTORY));
    }

    @Test
    public void shouldUpdateDeliveryStatus() throws OrderNotFoundException {
        //given //when
        when(orderHistoryDAO.findById(2L)).thenReturn(Optional.of(SECOND_ORDER_HISTORY));
        when(orderHistoryDAO.save(SECOND_ORDER_HISTORY)).thenReturn(SECOND_ORDER_HISTORY_WITH_UPDATED_STATUS);

        //then
        Assertions.assertEquals(SECOND_ORDER_HISTORY_WITH_UPDATED_STATUS, orderHistoryService.
                updateOrderDeliveryStatusById(2L, "DELIVERED"));
    }
}
