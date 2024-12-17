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
public class ShippingInfo {

  @Column(name = "SHIPPING_ADDRESS")
  private String shippingAddress;

  @Column(name = "SHIPPING_MANGER_ID")
  private String shippingManagerId;

  @Column(name = "SHIPPING_MANGER_SLACK_ID")
  private String shippingManagerSlackId;

  @Builder
  public ShippingInfo(
      String shippingAddress, String shippingManagerId, String shippingManagerSlackId) {
    this.shippingAddress = shippingAddress;
    this.shippingManagerId = shippingManagerId;
    this.shippingManagerSlackId = shippingManagerSlackId;
  }
}
