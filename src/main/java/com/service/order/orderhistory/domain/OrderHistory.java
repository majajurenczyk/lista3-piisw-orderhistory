package com.service.order.orderhistory.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.repository.cdi.Eager;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class OrderHistory {
    @Id
    private Long orderId;

    @Column
    private String customerName;

    @Column
    private String courierName;

    @Column
    private String deliveryStatus;

    @Column
    private String productNames;

    @Column
    private BigDecimal totalPrice;
}
