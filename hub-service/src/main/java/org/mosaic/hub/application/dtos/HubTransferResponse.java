package org.mosaic.hub.application.dtos;

import static lombok.AccessLevel.PRIVATE;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.mosaic.hub.domain.model.HubTransfer;

@Getter
@Builder(access = PRIVATE)
@AllArgsConstructor(access = PRIVATE)
public class HubTransferResponse {

  @JsonInclude(JsonInclude.Include.NON_NULL)
  private Long hubTransferId;
  private String departureHubName;
  private String arrivalHubName;
  private int estimatedTime;
  private double estimatedDistance;

  public static HubTransferResponse from(HubTransfer hubTransfer) {
    return HubTransferResponse.builder()
        .hubTransferId(hubTransfer.getId())
        .departureHubName(hubTransfer.getDepartureHub().getName())
        .arrivalHubName(hubTransfer.getArrivalHub().getName())
        .estimatedTime(hubTransfer.getEstimatedTime())
        .estimatedDistance(hubTransfer.getEstimatedDistance())
        .build();
  }
}
