package org.mosaic.hub.application.dtos;

import static lombok.AccessLevel.PRIVATE;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = PRIVATE)
public class UpdateHubTransferServiceRequest {

  private int estimatedTime;
  private double estimatedDistance;

  public static UpdateHubTransferServiceRequest create(
      int estimatedTime, double estimatedDistance) {
    return new UpdateHubTransferServiceRequest(estimatedTime, estimatedDistance);
  }
}
