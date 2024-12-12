package org.mosaic.hub.application.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.hibernate.validator.constraints.Range;

@Getter
@AllArgsConstructor
public class UpdateHubRequest {

  private Long managerId;

  @NotBlank
  private String name;

  @NotBlank
  private String address;

  @Range(min = 33, max = 39)
  private double latitude;

  @Range(min = 124, max = 132)
  private double longitude;
}
