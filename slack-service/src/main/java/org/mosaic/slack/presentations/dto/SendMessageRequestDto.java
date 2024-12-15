package org.mosaic.slack.presentations.dto;


import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class SendMessageRequestDto {

  private Long deliveryManagerId;
  private Long customerId;
  private Long orderId;
  private Long deliveryId;

}
