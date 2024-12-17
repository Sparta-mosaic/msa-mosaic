package org.mosaic.delivery_service.domain.entity.deliveryRoute;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.Duration;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.mosaic.delivery_service.domain.entity.delivery.Delivery;
import org.mosaic.delivery_service.domain.enums.RouteState;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "P_DELIVERY_ROUTE")
public class DeliveryRoute {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "DELIVERY_ROUTE_ID")
  private Long deliveryRouteId;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "DELIVERY_ID", nullable = false)
  private Delivery delivery;

  @Embedded private DeliveryRouteInfo routeInfo;

  @Enumerated(EnumType.STRING)
  @Column(name = "ROUTE_STATE")
  private RouteState routeState = RouteState.PENDING;

  @Column(name = "REAL_ELAPSED_TIME")
  private Duration realElapsedTime = null;

  @Column(name = "HUB_DELIVERY_DRIVER")
  private String hubDeliveryDriver = null;

  @Builder
  private DeliveryRoute(Delivery delivery, DeliveryRouteInfo routeInfo) {
    this.delivery = delivery;
    this.routeInfo = routeInfo;
  }
}
