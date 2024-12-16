package org.mosaic.slack.presentations.dto;


import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CreateMessageRequestDto {

  private String orderId;
  private String customerName;
  private String customerEmail;
  private String deliveryPersonName;
  private String deliveryPersonEmail;
  private String productInfo;
  private String departure;
  private String stopOver;
  private String arrival;
  private String deliveryDeadline;
  private String orderRequest;

}
