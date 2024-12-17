package org.mosaic.ai.presentation.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
@ToString
public class RequestPrompt {

  @NotBlank
  private String departure;
  @NotBlank
  private String arrival;
  private String stopOver;
  private String estimateTime;
  private String requestDeadline;

}
