package org.mosaic.hub.application.dtos;

import static lombok.AccessLevel.PRIVATE;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor(access = PRIVATE)
@NoArgsConstructor(access = PRIVATE)
public class HubPathResponse {

  private String hubUuid;
  private String hubName;
  private int time;

  public static HubPathResponse create(String hubUuid, String hubName, int time){
    return new HubPathResponse(hubUuid, hubName, time);
  }
}