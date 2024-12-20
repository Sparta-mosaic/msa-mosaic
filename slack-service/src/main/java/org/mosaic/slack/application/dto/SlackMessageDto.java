package org.mosaic.slack.application.dto;


import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class SlackMessageDto {

  private String orderId;
  private String customerName;
  private String customerEmail;
  private String deliveryPersonName;
  private String deliveryPersonEmail;
  private String productInfo;
  private String departure;
  private String arrival;
  private String deliveryDeadline;
  private String message;

  public static SlackMessageDto of(
      String orderId
      , String customerName
      , String customerEmail
      , String deliveryPersonName
      , String deliveryPersonEmail
      , String productInfo
      , String departure
      , String arrival
      , String deliveryDeadline
      , String message) {

    return SlackMessageDto.builder()
        .orderId(orderId)
        .customerName(customerName)
        .customerEmail(customerEmail)
        .deliveryPersonName(deliveryPersonName)
        .deliveryPersonEmail(deliveryPersonEmail)
        .productInfo(productInfo)
        .departure(departure)
        .arrival(arrival)
        .deliveryDeadline(deliveryDeadline)
        .message(message)
        .build();

  }


}
