package org.mosaic.auth.application.dtos;

import static lombok.AccessLevel.PRIVATE;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.mosaic.auth.domain.model.delivery_driver.DeliveryDriverType;

@Getter
@Builder(access = PRIVATE)
@AllArgsConstructor(access = PRIVATE)
public class CreateDeliveryDriverService {

  private Long userId;
  private String hubUuid;
  private String slackEmail;
  private DeliveryDriverType deliveryDriverType;

  public static CreateDeliveryDriverService create(
      Long userId, String hubUuid, String slackEmail,
      DeliveryDriverType deliveryDriverType) {
    return CreateDeliveryDriverService.builder()
        .userId(userId)
        .hubUuid(hubUuid)
        .slackEmail(slackEmail)
        .deliveryDriverType(deliveryDriverType)
        .build();
  }
}
