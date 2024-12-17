package org.mosaic.delivery_service.infrastructure.jpa;

import org.mosaic.delivery_service.domain.entity.delivery.Delivery;
import org.mosaic.delivery_service.domain.repository.DeliveryRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeliveryRepositoryImpl extends JpaRepository<Delivery, Long>, DeliveryRepository {}
