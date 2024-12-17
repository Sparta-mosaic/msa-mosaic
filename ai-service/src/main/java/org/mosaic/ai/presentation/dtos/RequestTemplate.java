package org.mosaic.ai.presentation.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RequestTemplate {

  @NotBlank
  private String template;

}
