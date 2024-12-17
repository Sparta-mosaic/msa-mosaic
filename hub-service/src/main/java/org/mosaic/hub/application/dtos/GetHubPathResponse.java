package org.mosaic.hub.application.dtos;

import static lombok.AccessLevel.PRIVATE;

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

  public static GetHubPathResponse from(
      List<HubPathResponse> hubPaths) {
    return new GetHubPathResponse(hubPaths, hubPaths.size());
  }
}