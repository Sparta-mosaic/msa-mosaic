package org.mosaic.delivery_service.domain.entity.delivery;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.mosaic.delivery_service.domain.entity.deliveryRoute.DeliveryRoute;
import org.mosaic.delivery_service.domain.entity.deliveryRoute.DeliveryRouteInfo;
import org.mosaic.delivery_service.domain.enums.DeliveryState;
import org.mosaic.delivery_service.libs.common.entity.BaseEntity;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "P_DELIVERY")
public class Delivery extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "DELIVERY_ID")
  private Long deliveryId;

  @Embedded private DeliveryInfo deliveryInfo;

  @Embedded private ShippingInfo shippingInfo;

  @Embedded private DeliveryTotalTimeDistance deliveryTotalTimeDistance;

  @OneToMany(mappedBy = "delivery", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<DeliveryRoute> routes = new ArrayList<>();

  @Enumerated(EnumType.STRING)
  @Column(name = "DELIVERY_STATE", nullable = false)
  private DeliveryState deliveryState = DeliveryState.PENDING;

  @Column(name = "SHIPPING_START_DATE")
  private LocalDateTime shippingStartDate;

  @Column(name = "SHIPPING_END_DATE")
  private LocalDateTime shippingEndDate;

  @Builder
  public Delivery(
      DeliveryInfo deliveryInfo,
      ShippingInfo shippingInfo,
      DeliveryTotalTimeDistance deliveryTotalTimeDistance,
      LocalDateTime shippingStartDate,
      LocalDateTime shippingEndDate) {
    this.deliveryInfo = deliveryInfo;
    this.shippingInfo = shippingInfo;
    this.deliveryTotalTimeDistance = deliveryTotalTimeDistance;
    this.shippingStartDate = shippingStartDate;
    this.shippingEndDate = shippingEndDate;
  }

  public void addRoute(DeliveryRouteInfo deliveryRouteInfo) {
    this.routes.add(DeliveryRoute.builder().delivery(this).routeInfo(deliveryRouteInfo).build());
  }
}
