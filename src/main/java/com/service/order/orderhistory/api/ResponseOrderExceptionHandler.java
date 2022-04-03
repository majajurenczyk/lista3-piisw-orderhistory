package com.service.order.orderhistory.api;

import com.service.order.orderhistory.api.message.ErrorResponse;
import com.service.order.orderhistory.exception.OrderNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ResponseOrderExceptionHandler {

    @ExceptionHandler({OrderNotFoundException.class})
    public ResponseEntity<ErrorResponse> handleException(OrderNotFoundException e) {
        return new ResponseEntity<>(new ErrorResponse("404", "History of order not found"), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({Exception.class})
    public ResponseEntity<ErrorResponse> handleException(Exception e) {
        return new ResponseEntity<>(new ErrorResponse("422",
                "History of order cannot be processed"), HttpStatus.UNPROCESSABLE_ENTITY);
    }
}
