package org.mosaic.slack.presentations.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@ToString
public class PromptRequest {

  @NotBlank
  private String departure;
  @NotBlank
  private String arrival;
  private String stopOver;
  private String estimateTime;
  private String requestDeadline;

  public static PromptRequest of(CreateMessageRequest dto) {
    return PromptRequest.builder()
        .departure(dto.getDeparture())
        .arrival(dto.getArrival())
        .stopOver(dto.getStopOver())
        .estimateTime(dto.getEstimateTime())
        .requestDeadline(dto.getDeliveryDeadline())
        .build();
  }

}
