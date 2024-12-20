package org.mosaic.slack.presentations.dto;


import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CreateMessageRequest {

  private String orderId;
  private String customerId;
  private String deliveryPersonId;
  private String productInfo;
  private String departure;
  private String stopOver;
  private String arrival;
  private String deliveryDeadline;
  private String estimateTime;


}
