package org.mosaic.delivery_service.domain.entity.deliveryRoute;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class DeliveryRouteInfo {
  @Column(name = "SEQUENCE", nullable = false)
  private Long sequence;

  @Column(name = "DEPARTURE_HUB_ID", nullable = false)
  private String departureHubId;

  @Column(name = "DEPARTURE_HUB_NAME", nullable = false)
  private String departureHubName;

  @Column(name = "ARRIVAL_HUB_ID", nullable = false)
  private String arrivalHubId;

  @Column(name = "ARRIVAL_HUB_NAME", nullable = false)
  private String arrivalHubName;

  @Column(name = "ESTIMATED_DISTANCE")
  private double estimatedDistance;

  @Column(name = "ESTIMATED_ELAPSED_TIME")
  private long estimatedElapsedTime;

  @Builder
  public DeliveryRouteInfo(
      Long sequence,
      String departureHubId,
      String departureHubName,
      String arrivalHubId,
      String arrivalHubName,
      double estimatedDistance,
      long estimatedElapsedTime) {
    this.sequence = sequence;
    this.departureHubId = departureHubId;
    this.departureHubName = departureHubName;
    this.arrivalHubId = arrivalHubId;
    this.arrivalHubName = arrivalHubName;
    this.estimatedDistance = estimatedDistance;
    this.estimatedElapsedTime = estimatedElapsedTime;
  }
}
