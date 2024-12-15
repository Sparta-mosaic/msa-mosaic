package org.mosaic.hub.application.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UpdateHubTransferServiceRequest {

  private int estimatedTime;
  private double estimatedDistance;

  public static UpdateHubTransferServiceRequest create(
      int estimatedTime, double estimatedDistance) {
    return new UpdateHubTransferServiceRequest(estimatedTime, estimatedDistance);
  }
}
