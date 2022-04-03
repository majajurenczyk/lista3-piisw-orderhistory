package com.service.order.orderhistory.repository;

import com.service.order.orderhistory.domain.OrderHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderHistoryDAO extends JpaRepository<OrderHistory, Long> {
}
