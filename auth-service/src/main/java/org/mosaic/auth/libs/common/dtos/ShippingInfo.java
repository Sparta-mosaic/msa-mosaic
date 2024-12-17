package org.mosaic.auth.libs.common.dtos;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ShippingInfo {

  private final String shippingAddress;
  private final String shippingManagerId;
  private final String shippingManagerSlackId;

  @Builder
  public ShippingInfo(
      String shippingAddress, String shippingManagerId, String shippingManagerSlackId) {
    this.shippingAddress = shippingAddress;
    this.shippingManagerId = shippingManagerId;
    this.shippingManagerSlackId = shippingManagerSlackId;
  }
}
