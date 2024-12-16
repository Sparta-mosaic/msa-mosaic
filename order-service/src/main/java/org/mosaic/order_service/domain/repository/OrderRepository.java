package org.mosaic.order_service.domain.repository;

import java.util.Optional;
import org.mosaic.order_service.domain.entity.Order;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository {

  Order save(Order newOrder);

  Optional<Order> findByOrderUuid(String uuid);
}
