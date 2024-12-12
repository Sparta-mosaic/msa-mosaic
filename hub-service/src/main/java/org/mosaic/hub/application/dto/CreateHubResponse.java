package org.mosaic.hub.application.dto;

import static lombok.AccessLevel.PRIVATE;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.mosaic.hub.domain.model.Hub;

@Getter
@Builder(access = PRIVATE)
@AllArgsConstructor
public class CreateHubResponse {

  private Long hubId;
  private String hubUuid;
  private Long managerId;
  private String name;
  private String address;
  private double latitude;
  private double longitude;
  private LocalDateTime createdAt;
  private String createdBy;
  private boolean isPublic;

  public static CreateHubResponse from(Hub hub) {
    return CreateHubResponse.builder()
        .hubId(hub.getId())
        .hubUuid(hub.getUuid())
        .managerId(hub.getManagerId())
        .name(hub.getName())
        .address(hub.getAddress())
        .latitude(hub.getCoordinates().getLatitude())
        .longitude(hub.getCoordinates().getLongitude())
        .createdAt(hub.getCreatedAt())
        .createdBy(hub.getCreatedBy())
        .isPublic(hub.isPublic())
        .build();
  }
}
