package com.service.order.orderhistory.api.message;

import lombok.Value;

@Value
public class ErrorResponse {
    String statusCode;
    String message;
}
