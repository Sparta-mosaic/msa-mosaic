package org.mosaic.order_service.infrastructure.repository;

import org.mosaic.order_service.domain.entity.Order;
import org.mosaic.order_service.domain.repository.OrderRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepositoryImpl extends JpaRepository<Order, Long>, OrderRepository {}
