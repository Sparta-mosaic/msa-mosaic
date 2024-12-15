package org.mosaic.order_service.domain.repository;


import org.mosaic.order_service.domain.entity.Order;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository {

  Order save(Order newOrder);
}
