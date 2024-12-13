package org.mosaic.hub.application.dtos;

import static lombok.AccessLevel.PRIVATE;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder(access = PRIVATE)
@AllArgsConstructor(access = PRIVATE)
public class CreateHubServiceRequest {

  private Long managerId;
  private String name;
  private String address;
  private double latitude;
  private double longitude;

  public static CreateHubServiceRequest create(
      Long managerId, String name, String address,
      double latitude, double longitude) {
    return CreateHubServiceRequest.builder()
        .managerId(managerId)
        .name(name)
        .address(address)
        .latitude(latitude)
        .longitude(longitude)
        .build();
  }
}
