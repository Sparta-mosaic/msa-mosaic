package org.mosaic.product_service.presentaion.dtos;

import lombok.Builder;
import lombok.Getter;

@Getter
public class OrderProductUuidRequest {
  private final String supplierCompanyId;
  private final String receiverCompanyId;

  @Builder
  private OrderProductUuidRequest(String supplierCompanyId, String receiverCompanyId) {
    this.supplierCompanyId = supplierCompanyId;
    this.receiverCompanyId = receiverCompanyId;
  }
}
