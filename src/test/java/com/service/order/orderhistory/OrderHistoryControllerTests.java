package com.service.order.orderhistory;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.service.order.orderhistory.api.OrderHistoryController;
import com.service.order.orderhistory.domain.OrderHistory;
import com.service.order.orderhistory.service.OrderHistoryService;
import com.service.order.orderhistory.service.OrderHistoryServiceAPI;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;

import static com.service.order.orderhistory.OrderHistoryTestDataProvider.*;
import static org.mockito.ArgumentMatchers.notNull;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = OrderHistoryController.class)
public class OrderHistoryControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    OrderHistoryServiceAPI orderHistoryService;

    @InjectMocks
    OrderHistoryController orderHistoryController;

    @Test
    void shouldGetOrderHistoryById() throws Exception {
        //given //when
        when(orderHistoryService.getOrderById(1L)).thenReturn(FIRST_ORDER_HISTORY);
        MockHttpServletResponse resp = mockMvc.perform(get("/orders-history/{id}", 1L))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse();

        //then
        Assertions.assertEquals(HttpStatus.OK.value(), resp.getStatus());
        Assertions.assertEquals(objectMapper.writeValueAsString(FIRST_ORDER_HISTORY), resp.getContentAsString());
    }

    @Test
    void shouldAddNewOrderHistory() throws Exception {
        //given //when
        when(orderHistoryService.addOrderHistory((OrderHistory) notNull())).thenReturn(FIRST_ORDER_HISTORY);

        MockHttpServletResponse resp = mockMvc.perform(
                post("/orders-history")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(NEW_ORDER_HISTORY)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse();

        //then
        Assertions.assertEquals(HttpStatus.CREATED.value(), resp.getStatus());
        Assertions.assertEquals(objectMapper.writeValueAsString(FIRST_ORDER_HISTORY), resp.getContentAsString());
    }

    @Test
    void shouldUpdateOrderDeliveryStatus() throws Exception {
        //given //when
        when(orderHistoryService.updateOrderDeliveryStatusById(2L, "DELIVERED"))
                .thenReturn(SECOND_ORDER_HISTORY_WITH_UPDATED_STATUS);

        MockHttpServletResponse resp = mockMvc.perform(
                        post("/orders-history/" + 2 + "/delivery")
                                .param("deliveryStatus", "DELIVERED"))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse();

        //then
        Assertions.assertEquals(HttpStatus.OK.value(), resp.getStatus());
        Assertions.assertEquals(objectMapper.writeValueAsString(SECOND_ORDER_HISTORY_WITH_UPDATED_STATUS),
                resp.getContentAsString());
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
