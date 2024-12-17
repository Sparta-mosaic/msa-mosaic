package org.mosaic.delivery_service.domain.entity.delivery;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class DeliveryTotalTimeDistance {
  @Column(name = "TOTAL_DISTANCE", nullable = false)
  private long totalDistance;

  @Column(name = "TOTAL_ELAPSED_TIME", nullable = false)
  private double totalElapsedTime;

  @Builder
  private DeliveryTotalTimeDistance(long totalDistance, double totalElapsedTime) {
    this.totalElapsedTime = totalElapsedTime;
    this.totalDistance = totalDistance;
  }
}
