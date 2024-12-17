package org.mosaic.delivery_service.infrastructure.client.dtos;

import static lombok.AccessLevel.*;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = PRIVATE)
public class GetHubPathResponse {

  private List<HubPathResponse> hubPaths;
  private int total;

  public static GetHubPathResponse from(List<HubPathResponse> hubPaths) {
    return new GetHubPathResponse(hubPaths, hubPaths.size());
  }
}
