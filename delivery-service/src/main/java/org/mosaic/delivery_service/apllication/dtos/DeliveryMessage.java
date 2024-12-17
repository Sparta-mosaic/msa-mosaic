package org.mosaic.delivery_service.apllication.dtos;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.Getter;

@Getter
public class DeliveryMessage {
  private String orderId;
  private String userId;
  private List<CompanyHubUuidDto> companyHubUuidDtos;

  @JsonCreator
  public DeliveryMessage(
      @JsonProperty("orderId") String orderId,
      @JsonProperty("userId") String userId,
      @JsonProperty("companyHubUuidDtos") List<CompanyHubUuidDto> companyHubUuidDtos) {
    this.orderId = orderId;
    this.userId = userId;
    this.companyHubUuidDtos = companyHubUuidDtos;
  }
}
