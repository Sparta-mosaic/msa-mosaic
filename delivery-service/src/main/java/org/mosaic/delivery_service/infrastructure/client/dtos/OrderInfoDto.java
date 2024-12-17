package org.mosaic.delivery_service.infrastructure.client.dtos;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;

@Getter
public class OrderInfoDto {
  private String supplierCompanyId;
  private String receiverCompanyId;
  private LocalDateTime orderDate;

  @Builder
  private OrderInfoDto(
      String supplierCompanyId, String receiverCompanyId, LocalDateTime orderDate) {
    this.supplierCompanyId = supplierCompanyId;
    this.receiverCompanyId = receiverCompanyId;
    this.orderDate = orderDate;
  }
}
