package org.mosaic.hub.application.dtos;

import static lombok.AccessLevel.PRIVATE;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.mosaic.hub.domain.model.Hub;

@Getter
@Builder(access = PRIVATE)
@AllArgsConstructor(access = PRIVATE)
@NoArgsConstructor(access = PRIVATE)
public class HubResponse {

  private Long hubId;
  private String hubUuid;
  private Long managerId;
  private String name;
  private String address;
  private double latitude;
  private double longitude;
  private LocalDateTime createdAt;
  private String createdBy;
  private LocalDateTime updatedAt;
  private String updatedBy;
  private Boolean isPublic;

  public static HubResponse from(Hub hub) {
    return HubResponse.builder()
        .hubId(hub.getId())
        .hubUuid(hub.getUuid())
        .managerId(hub.getManagerId())
        .name(hub.getName())
        .address(hub.getAddress())
        .latitude(hub.getCoordinates().getLatitude())
        .longitude(hub.getCoordinates().getLongitude())
        .createdAt(hub.getCreatedAt())
        .createdBy(hub.getCreatedBy())
        .updatedAt(hub.getUpdatedAt())
        .updatedBy(hub.getUpdatedBy())
        .isPublic(hub.isPublic())
        .build();
  }
}
