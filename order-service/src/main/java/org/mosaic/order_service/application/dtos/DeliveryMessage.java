package org.mosaic.order_service.application.dtos;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DeliveryMessage {
  private String orderId;
  private String userId;
  private List<CompanyHubUuidDto> companyHubUuidDtos;
}
