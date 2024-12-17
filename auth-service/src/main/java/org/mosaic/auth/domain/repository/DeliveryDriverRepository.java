package org.mosaic.auth.domain.repository;

import org.mosaic.auth.domain.model.delivery_driver.DeliveryDriver;
import org.mosaic.auth.domain.model.delivery_driver.DeliveryDriverType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeliveryDriverRepository extends JpaRepository<DeliveryDriver, Long> {

  int countByType(DeliveryDriverType type);
}
