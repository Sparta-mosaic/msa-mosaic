package org.mosaic.delivery_service.infrastructure.client.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class CommonResponse<T> {
  private boolean success;
  private T response;
}
