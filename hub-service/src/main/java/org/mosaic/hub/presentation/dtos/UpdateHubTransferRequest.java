package org.mosaic.hub.presentation.dtos;

import static lombok.AccessLevel.PRIVATE;

import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.mosaic.hub.application.dtos.UpdateHubTransferServiceRequest;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = PRIVATE)
public class UpdateHubTransferRequest {

  @PositiveOrZero
  private int estimatedTime;

  @PositiveOrZero
  private double estimatedDistance;

  public UpdateHubTransferServiceRequest toService() {
    return UpdateHubTransferServiceRequest.create(estimatedTime, estimatedDistance);
  }
}
