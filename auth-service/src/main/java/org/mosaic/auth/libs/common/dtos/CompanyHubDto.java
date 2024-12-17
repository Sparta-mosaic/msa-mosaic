package org.mosaic.auth.libs.common.dtos;

import lombok.Builder;
import lombok.Getter;

@Getter
public class CompanyHubDto {
  private final String departureHubId;
  private final String arrivalHubId;

  @Builder
  public CompanyHubDto(String departureHubId, String arrivalHubId) {
    this.departureHubId = departureHubId;
    this.arrivalHubId = arrivalHubId;
  }
}
