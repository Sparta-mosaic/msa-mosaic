package org.mosaic.delivery_service.infrastructure.client.dtos;

import static lombok.AccessLevel.*;

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
  private double distance;
}
