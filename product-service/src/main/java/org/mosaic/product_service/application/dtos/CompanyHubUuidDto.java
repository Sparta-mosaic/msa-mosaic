package org.mosaic.product_service.application.dtos;

import lombok.Builder;
import lombok.Getter;

@Getter
public class CompanyHubUuidDto {
  private final String productUuid;
  private final String productHubId;

  private final String companyId;

  @Builder
  public CompanyHubUuidDto(String productUuid, String productHubId, String companyId) {
    this.productUuid = productUuid;
    this.productHubId = productHubId;
    this.companyId = companyId;
  }
}
