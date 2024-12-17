package org.mosaic.auth.presentation.dtos;

import static lombok.AccessLevel.PRIVATE;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.mosaic.auth.application.dtos.CreateDeliveryDriverService;
import org.mosaic.auth.domain.model.delivery_driver.DeliveryDriverType;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = PRIVATE)
public class CreateDeliveryDriverRequest {

  @Positive
  @NotNull
  private Long userId;

  private String hubUuid;

  @NotBlank
  private String slackEmail;

  @NotNull
  private DeliveryDriverType deliveryDriverType;

  public CreateDeliveryDriverService toService() {
    return CreateDeliveryDriverService.create(
        userId, hubUuid, slackEmail, deliveryDriverType);
  }
}
