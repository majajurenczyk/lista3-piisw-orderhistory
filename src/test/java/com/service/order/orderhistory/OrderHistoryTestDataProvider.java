package com.service.order.orderhistory;

import com.service.order.orderhistory.domain.OrderHistory;

import java.math.BigDecimal;

public class OrderHistoryTestDataProvider {
    public static OrderHistory FIRST_ORDER_HISTORY = new OrderHistory(
            1L,
            "Maja",
            "Darek",
            "PICKED_UP",
            "suszarka, spodnie",
            BigDecimal.valueOf(14.00)
    );

    public static OrderHistory SECOND_ORDER_HISTORY = new OrderHistory(
            2L,
            "Maja",
            "Darek",
            "PICKED_UP",
            "suszarka, spodnie",
            BigDecimal.valueOf(14.54)
    );

    public static OrderHistory SECOND_ORDER_HISTORY_WITH_UPDATED_STATUS = new OrderHistory(
            2L,
            "Maja",
            "Darek",
            "DELIVERED",
            "suszarka, spodnie",
            BigDecimal.valueOf(14.54)
    );

    public static OrderHistory NEW_ORDER_HISTORY = new OrderHistory(
            null,
            "Maja",
            "Darek",
            "PICKED_UP",
            "suszarka, spodnie",
            BigDecimal.valueOf(14.00)
    );
}
