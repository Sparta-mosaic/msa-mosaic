package org.mosaic.auth.domain.repository;

import java.util.Optional;
import org.mosaic.auth.domain.model.delivery_driver.DeliveryDriver;
import org.mosaic.auth.domain.model.delivery_driver.DeliveryDriverType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface DeliveryDriverRepository extends JpaRepository<DeliveryDriver, Long> {

  int countByType(DeliveryDriverType type);

  @Query("""
        SELECT dd
        FROM DeliveryDriver dd
        WHERE dd.type = :type
          AND (:hubUuid IS NULL OR dd.hubUuid = :hubUuid)
          AND dd.isLatest = TRUE
    """)
  Optional<DeliveryDriver> findLatestDriver(DeliveryDriverType type, String hubUuid);

  @Query("""
        SELECT dd
        FROM DeliveryDriver dd
        WHERE dd.type = :type
          AND (:hubUuid IS NULL OR dd.hubUuid = :hubUuid)
          AND dd.deliveryOrder > :currentOrder
        ORDER BY dd.deliveryOrder ASC
        LIMIT 1
        """)
  Optional<DeliveryDriver> findNextDriver(DeliveryDriverType type, String hubUuid, int currentOrder);

  @Query("""
        SELECT d
        FROM DeliveryDriver d
        WHERE d.type = :type
          AND (:hubUuid IS NULL OR d.hubUuid = :hubUuid)
        ORDER BY d.deliveryOrder ASC
        LIMIT 1
    """)
  Optional<DeliveryDriver> findFirstDriver(DeliveryDriverType type, String hubUuid);
}