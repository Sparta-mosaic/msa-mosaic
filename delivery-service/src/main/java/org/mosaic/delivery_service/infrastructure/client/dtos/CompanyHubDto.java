package org.mosaic.delivery_service.infrastructure.client.dtos;

import lombok.Builder;
import lombok.Getter;

@Getter
public class CompanyHubDto {
  private String departureHubId;
  private String arrivalHubId;

  @Builder
  public CompanyHubDto(String departureHubId, String arrivalHubId) {
    this.departureHubId = departureHubId;
    this.arrivalHubId = arrivalHubId;
  }
}
