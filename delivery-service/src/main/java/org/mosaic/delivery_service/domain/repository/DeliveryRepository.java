package org.mosaic.delivery_service.domain.repository;

import org.mosaic.delivery_service.domain.entity.delivery.Delivery;
import org.springframework.stereotype.Repository;

@Repository
public interface DeliveryRepository {

  Delivery save(Delivery delivery);
}
