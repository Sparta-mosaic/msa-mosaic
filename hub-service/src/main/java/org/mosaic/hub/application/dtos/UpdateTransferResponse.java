package org.mosaic.hub.application.dtos;

import static lombok.AccessLevel.PRIVATE;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.mosaic.hub.domain.model.HubTransfer;

@Getter
@Builder(access = PRIVATE)
@AllArgsConstructor(access = PRIVATE)
public class UpdateTransferResponse {

  private Long hubTransferId;
  private String hubTransferUuid;
  private int estimatedTime;
  private double estimatedDistance;
  private LocalDateTime createdAt;
  private String createdBy;
  private LocalDateTime updatedAt;
  private String updatedBy;
  private boolean isPublic;


  public static UpdateTransferResponse from(HubTransfer hubTransfer) {
    return UpdateTransferResponse.builder()
        .hubTransferId(hubTransfer.getId())
        .hubTransferUuid(hubTransfer.getUuid())
        .estimatedTime(hubTransfer.getEstimatedTime())
        .estimatedDistance(hubTransfer.getEstimatedDistance())
        .createdAt(hubTransfer.getCreatedAt())
        .createdBy(hubTransfer.getCreatedBy())
        .updatedAt(hubTransfer.getUpdatedAt())
        .updatedBy(hubTransfer.getUpdatedBy())
        .isPublic(hubTransfer.isPublic())
        .build();
  }
}
