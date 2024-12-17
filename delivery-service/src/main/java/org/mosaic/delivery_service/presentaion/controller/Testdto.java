package org.mosaic.delivery_service.presentaion.controller;

import java.util.List;
import lombok.Builder;
import lombok.Getter;
import org.mosaic.delivery_service.apllication.dtos.CompanyHubUuidDto;

@Getter
public class Testdto {

  private String orderId;
  private String userId;
  private List<CompanyHubUuidDto> companyHubUuidDtos;

  @Builder
  public Testdto(String orderId, String userId, List<CompanyHubUuidDto> companyHubUuidDtos) {
    this.orderId = orderId;
    this.userId = userId;
    this.companyHubUuidDtos = companyHubUuidDtos;
  }
}
