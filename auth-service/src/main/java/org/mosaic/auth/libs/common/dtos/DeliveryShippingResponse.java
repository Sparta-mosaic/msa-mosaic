package org.mosaic.auth.libs.common.dtos;

import lombok.Builder;
import lombok.Getter;

@Getter
public class DeliveryShippingResponse {

  private final CompanyHubDto companyHubDto;
  private final ShippingInfo shippingInfo;

  @Builder
  public DeliveryShippingResponse(CompanyHubDto companyHubDto, ShippingInfo shippingInfo) {
    this.companyHubDto = companyHubDto;
    this.shippingInfo = shippingInfo;
  }
}
