package org.mosaic.delivery_service.infrastructure.client.dtos;

import lombok.Builder;
import lombok.Getter;
import org.mosaic.delivery_service.domain.entity.delivery.ShippingInfo;

@Getter
public class DeliveryShippingResponse {

  private CompanyHubDto companyHubDto;
  private ShippingInfo shippingInfo;

  @Builder
  public DeliveryShippingResponse(CompanyHubDto companyHubDto, ShippingInfo shippingInfo) {
    this.companyHubDto = companyHubDto;
    this.shippingInfo = shippingInfo;
  }
}
