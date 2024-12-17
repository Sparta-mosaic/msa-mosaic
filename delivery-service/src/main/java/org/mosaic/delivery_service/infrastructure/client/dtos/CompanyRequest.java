package org.mosaic.delivery_service.infrastructure.client.dtos;

import lombok.Builder;
import lombok.Getter;

@Getter
public class CompanyRequest {
  private final String supplierCompanyId;
  private final String receiverCompanyId;

  @Builder
  private CompanyRequest(String supplierCompanyId, String receiverCompanyId) {
    this.supplierCompanyId = supplierCompanyId;
    this.receiverCompanyId = receiverCompanyId;
  }
}
